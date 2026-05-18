package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView list;
    Button btntbh, btnlog;

    ArrayList<roduct> productList = new ArrayList<>();

    // 1. DIUBAH: Menggunakan penampung teks String dan ArrayAdapter bawaan Android
    ArrayList<String> productNames = new ArrayList<>();
    ArrayAdapter<String> adapter;

    String URL_TAMPIL = "http://10.0.2.2:81/koneksi_icikiwir/get_produk.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = findViewById(R.id.list);
        btntbh = findViewById(R.id.btntbh);
        btnlog = findViewById(R.id.btnlog);

        // 2. DIUBAH: Setup adapter standar menggunakan layout default android simple_list_item_1
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productNames);
        list.setAdapter(adapter);

        btntbh.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, TambahroductActivity.class);
            startActivity(intent);
        });

        btnlog.setOnClickListener(view -> {
            getSharedPreferences("login", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            Toast.makeText(HomeActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        loadroduct();
    }

    private void loadroduct() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL_TAMPIL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if (status.equals("success")) {
                            productList.clear();
                            productNames.clear(); // Bersihkan daftar teks lama

                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                roduct product = new roduct(
                                        obj.getString("Id"),
                                        obj.getString("CategoryId"),
                                        obj.getString("Name"),
                                        obj.getString("Description"),
                                        obj.getString("Price"),
                                        obj.getString("Stock"),
                                        obj.getString("ImageUrl")
                                );

                                productList.add(product);

                                // 3. DITAMBAHKAN: Menggabungkan data objek menjadi teks String multi-baris
                                String infoProduk = product.getName() + "\nHarga: Rp " + product.getPrice() + "\nLink Foto: " + product.getImageUrl();
                                productNames.add(infoProduk);
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(HomeActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(HomeActivity.this, "JSON Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(HomeActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}

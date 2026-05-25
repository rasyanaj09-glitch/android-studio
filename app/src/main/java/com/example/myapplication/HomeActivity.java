package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    ListView list;
    Button btntbh, btnlog;

    ArrayList<roduct> productList = new ArrayList<>();

    // GANTI ADAPTER: Menggunakan Custom Adapter tombol baris baru
    ProductAdapter adapter;

    String URL_TAMPIL = "http://10.0.2.2:81/koneksi_icikiwir/get_produk.php";
    String URL_DELETE = "http://10.0.2.2:81/koneksi_icikiwir/delete.php";;

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

       strukturnya:
        adapter = new ProductAdapter(this, productList, new ProductAdapter.OnProductActionListener() {

            @Override
            public void onItemClick(roduct product) {

                Toast.makeText(HomeActivity.this, "Anda memilih: " + product.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEdit(roduct product) {
                Intent intent = new Intent(HomeActivity.this, editactivity.class);
                intent.putExtra("IS_UPDATE", true);
                intent.putExtra("PRODUCT_DATA", product);
                startActivity(intent);
            }

            @Override
            public void onDelete(roduct product, int position) {
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Hapus Produk")
                        .setMessage("Apakah Anda yakin ingin menghapus " + product.getName() + "?")
                        .setPositiveButton("Ya", (d, w) -> sukilete(product, position))
                        .setNegativeButton("Tidak", null)
                        .show();
            }
        });

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

        talok();
    }

    private void sukilete(roduct product, int position) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL_DELETE,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String status = obj.getString("status");
                        String message = obj.getString("message");

                        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();

                        if (status.equals("success") || status.equals("sukses")) {

                            productList.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Toast.makeText(HomeActivity.this, "JSON Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(HomeActivity.this, "Error koneksi hapus: " + error.toString(), Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(product.getId()));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    private void talok() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL_TAMPIL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if (status.equals("success")) {
                            productList.clear();

                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                roduct product = new roduct(
                                        obj.getInt("Id"),
                                        obj.getString("Name"),
                                        obj.getString("Price"),
                                        obj.getInt("Stock")
                                );

                                productList.add(product);
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

    @Override
    protected void onResume() {
        super.onResume();
        talok();
    }
}

package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahroductActivity extends AppCompatActivity {


    EditText etName, etPrice, etStock;
    Button btnSaveProduct;

    String URL_TAMBAH = "http://10.0.2.2:81/koneksi_icikiwir/tambah.php";
    String URL_UPDATE = "http://10.0.2.2:81/koneksi_icikiwir/update.php";

    boolean isUpdate = false;
    roduct productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambahroduct);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi komponen UI yang valid
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etStock = findViewById(R.id.etStock);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);

        // Menangkap data kiriman lembaran Intent dari HomeActivity
        isUpdate = getIntent().getBooleanExtra("IS_UPDATE", false);
        productData = (roduct) getIntent().getSerializableExtra("PRODUCT_DATA");

        // Jika dalam mode update, langsung panggil pengisian data form
        if (isUpdate) {
            setupDataForUpdate();
        }

        btnSaveProduct.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String stock = etStock.getText().toString().trim();

            // Validasi input minimal (Hanya memvalidasi Name, Price, dan Stock)
            if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                Toast.makeText(this, "Aduh jan kosong lah", Toast.LENGTH_SHORT).show();
                return;
            }

            String URL_TARGET = isUpdate ? URL_UPDATE : URL_TAMBAH;

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    URL_TARGET,
                    response -> {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");
                            String message = obj.getString("message");

                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                            if (status.equals("success") || status.equals("sukses")) {
                                finish();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "JSON ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Toast.makeText(this, "Error koneksi: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    // JIKA MODE UPDATE: Kirimkan ID data primer ke server backend PHP
                    if (isUpdate && productData != null) {
                        params.put("Id", String.valueOf(productData.getId()));
                    }

                    // Parameter disesuaikan dengan struktur variabel model 'roduct' terbaru Anda
                    params.put("Name", name);
                    params.put("Price", price);
                    params.put("Stock", stock);
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(request);
        });
    }

    private void setupDataForUpdate() {
        if (isUpdate && productData != null) {
            btnSaveProduct.setText("Simpan Perubahan");

            etName.setText(productData.getName());
            etPrice.setText(productData.getPrice());
            etStock.setText(String.valueOf(productData.getStock()));
        }
    }
}

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

public class editactivity extends AppCompatActivity {

    EditText etName, etPrice, etStock;
    Button btnSaveProduct;


    String URL_UPDATE = "http://10.0.2.2:81/koneksi_icikiwir/update.php";


    roduct productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etStock = findViewById(R.id.etStock);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);


        productData = (roduct) getIntent().getSerializableExtra("PRODUCT_DATA");


        setupDataForUpdate();

        btnSaveProduct.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String stock = etStock.getText().toString().trim();

            if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                Toast.makeText(this, "Aduh jan kosong lah", Toast.LENGTH_SHORT).show();
                return;
            }

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    URL_UPDATE,
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
                    error -> Toast.makeText(this, "Error koneksi: " + error.toString(), Toast.LENGTH_SHORT).show()
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();


                    if (productData != null) {
                        params.put("Id", String.valueOf(productData.getId()));
                    }

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
        if (productData != null) {
            btnSaveProduct.setText("Simpan Perubahan");
            etName.setText(productData.getName());
            etPrice.setText(productData.getPrice());
            etStock.setText(String.valueOf(productData.getStock()));
        }
    }
}

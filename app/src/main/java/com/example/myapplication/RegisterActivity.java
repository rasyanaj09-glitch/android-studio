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

public class RegisterActivity extends AppCompatActivity {

    EditText etEmailL, etPasswordL, etName;
    Button btnsimpan;

    String URL = "http://10.0.2.2:81/koneksi_icikiwir/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmailL = findViewById(R.id.etEmailL);
        etPasswordL = findViewById(R.id.etPasswordL);
        etName = findViewById(R.id.etName);
        btnsimpan = findViewById(R.id.btnsimpan);

        btnsimpan.setOnClickListener(view -> {
            String email = etEmailL.getText().toString().trim();
            String password = etPasswordL.getText().toString().trim();
            String fullName = etName.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(this, "Aduh jan kosong lah", Toast.LENGTH_SHORT).show();
                return;
            }

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    URL,
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

                    params.put("Email", email);
                    params.put("Password", password);
                    params.put("FullName", fullName);
                    return params;
                }
            };


            Volley.newRequestQueue(this).add(request);
        });
    }
}

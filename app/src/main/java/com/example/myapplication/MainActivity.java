package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.EditText;
=======
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
>>>>>>> f0cdb8b9331b91910eb04f8e8ecc03007a1481bb
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etEmailL, etPasswordL;
    Button btnLogin, btnregis;

    String URL = "http://10.0.2.2:81/koneksi_icikiwir/login.php";

    @Override
    protected void onStart(){
        super.onStart();

        boolean isLogin = getSharedPreferences("login", MODE_PRIVATE)
                .getBoolean("isLogin", false);
        if (isLogin){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

=======
public class MainActivity extends AppCompatActivity {
EditText editnama,editemail,editpassword;
Button btn;

RadioGroup radioGroup;
>>>>>>> f0cdb8b9331b91910eb04f8e8ecc03007a1481bb
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

=======
>>>>>>> f0cdb8b9331b91910eb04f8e8ecc03007a1481bb
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
<<<<<<< HEAD


        etEmailL = findViewById(R.id.etEmailL);
        etPasswordL = findViewById(R.id.etPasswordL);
        btnLogin = findViewById(R.id.btnLogin);
        btnregis = findViewById(R.id.btnregis);

        btnregis.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        btnLogin.setOnClickListener(view -> {
            String Email = etEmailL.getText().toString().trim();
            String Password = etPasswordL.getText().toString().trim();

            if (Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(this, "Harus Diisi Semua", Toast.LENGTH_SHORT).show();
                return;
            }

            btnLogin.setEnabled(false);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    URL,
                    response -> {
                        btnLogin.setEnabled(true);
                        try {
                            JSONObject obj = new JSONObject(response);
                            String status = obj.getString("status");

                            if (status.equals("success")) {
                                getSharedPreferences("login", MODE_PRIVATE)
                                        .edit()
                                        .putBoolean("isLogin", true)
                                        .putString("Email", Email)
                                        .apply();

                                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "JSON ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        btnLogin.setEnabled(true);
                        Toast.makeText(this, "error koneksi: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Email", Email);
                    params.put("Password", Password);
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(request);
        });
    }
}
=======
        editnama = findViewById(R.id.nama);
        editemail = findViewById(R.id.email);
        editpassword = findViewById(R.id.password);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> { // Gunakan 'view' kecil (best practice)
            String nama = editnama.getText().toString().trim();
            String email = editemail.getText().toString().trim();
            String password = editpassword.getText().toString().trim();

            // Gunakan || (OR) supaya kalau SALAH SATU kosong, langsung kena tegur
            if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Wajib di isi semua ya diks", Toast.LENGTH_SHORT).show();
            }
            // Gunakan && (Double AND) untuk logika yang benar
            else if (nama.equals("budak1") && password.equals("aduhai") && email.equals("mamang")) {
                Toast.makeText(this, "Berhasil loh ya re", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainRongngawi.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Gagal cik, cek lagi datanya", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
>>>>>>> f0cdb8b9331b91910eb04f8e8ecc03007a1481bb

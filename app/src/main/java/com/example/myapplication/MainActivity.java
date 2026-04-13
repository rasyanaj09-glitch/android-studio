package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText editnama,editemail,editpassword;
Button btn;

RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editnama = findViewById(R.id.nama);
        editemail = findViewById(R.id.email);
        editpassword = findViewById(R.id.password);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(View -> {
         String nama =editnama.getText().toString().trim();
            String email =editemail.getText().toString().trim();
            String password =editpassword.getText().toString().trim();

            if(nama.isEmpty() & email.isEmpty() & password.isEmpty()){
                Toast.makeText(this,"Wajib di isi semua ya diks",Toast.LENGTH_SHORT).show();
            } else if (nama.equals("budak1") &&password.equals("aduhai")&&email.equals("mamang"))
            {
                Toast.makeText(this,"Berhasil loh ya re",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainRongngawi.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this,"Gagal cik",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
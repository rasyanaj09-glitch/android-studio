package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class jeskejes extends AppCompatActivity {
EditText judulkejes,isijes,linkjes,vidiokejes;
Button btnbdk;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jeskejes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        judulkejes=findViewById(R.id.judulkejes);
        isijes=findViewById(R.id.isijes);
        linkjes=findViewById(R.id.linkjes);
        vidiokejes=findViewById(R.id.vidiokejes);
        btnbdk=findViewById(R.id.btnbdk);

        btnbdk.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("judul",judulkejes.getText().toString());
            intent.putExtra("isi",isijes.getText().toString());
            intent.putExtra("link",linkjes.getText().toString());
            intent.putExtra("vidio",vidiokejes.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        });


    }
}
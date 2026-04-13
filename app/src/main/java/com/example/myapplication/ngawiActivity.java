package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ngawiActivity extends AppCompatActivity {
TextView txtJudul,txtIsi;
Button btnLink,btnVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ngawi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtJudul = findViewById(R.id.txtJudul);
        txtIsi = findViewById(R.id.txtIsi);
        btnLink = findViewById(R.id.btnLink);
        btnVideo = findViewById(R.id.btnVideo);

        String judul=getIntent().getStringExtra("judul");
        String isi=getIntent().getStringExtra("isi");
        String link=getIntent().getStringExtra("link");
        String video=getIntent().getStringExtra("video");

        txtJudul.setText(judul);
        txtIsi.setText(isi);

        btnLink.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(i);
        });
        btnVideo.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(video));
            startActivity(i);
        });
    }
}
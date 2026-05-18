package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainRongngawi extends AppCompatActivity {
ListView listngawi;
ArrayList<String>listjudul;
ArrayAdapter<String>adapter;

Button tambahbdkbr;
ArrayList<String>listisi;
    ArrayList<String>listlink;
    ArrayList<String>listvidio;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_rongngawi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tambahbdkbr=findViewById(R.id.tambahbdkbr);
        listngawi=findViewById(R.id.listngawi);

        listjudul=new ArrayList<>();
        listisi=new ArrayList<>();
        listlink=new ArrayList<>();
        listvidio=new ArrayList<>();

        listjudul.add("Korban Perang Dunia I (1914-1918)");
        listisi.add("15 hingga 22 juta kematian (militer dan sipil) dan lebih dari 20 juta orang luka-luka....");
        listlink.add("https://id.wikipedia.org/wiki/Perang_Dunia_I");
        listvidio.add("https://youtu.be/yFQrh_KK2ws?si=EoiH8XbmxRcXWiCR");


        listjudul.add("Korban Perang Dunia II (1939-1945) ");
        listisi.add("korban jiwa diperkirakan mencapai 70 hingga lebih dari 85 juta orang....");
        listlink.add( "https://id.wikipedia.org/wiki/Perang_Dunia_II");
        listvidio.add( "https://youtu.be/fuNjaA7RUrE?si=sdUnXOYerAIbQMfh");
        adapter=new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,listjudul
        );
        listngawi.setAdapter(adapter);
        listngawi.setOnItemClickListener((parent, view, position, id) -> {


            Intent intent = new Intent(MainRongngawi.this, ngawiActivity.class);

            intent.putExtra("judul", listjudul.get(position));
            intent.putExtra("isi", listisi.get(position));
            intent.putExtra("link", listlink.get(position));
            intent.putExtra("video", listvidio.get(position));

            startActivity(intent);
        });
        tambahbdkbr.setOnClickListener(view -> {
            Intent intent = new Intent(MainRongngawi.this,jeskejes.class);
            startActivityForResult(intent,1);
       });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {
            String judul = data.getStringExtra("judul");
            String isi = data.getStringExtra("isi");
            String link = data.getStringExtra("link");
            String vidio = data.getStringExtra("vidio");
                listjudul.add(judul);
                listisi.add(isi);
                listlink.add(link);
                listvidio.add(vidio);
                adapter.notifyDataSetChanged();

        }
    }

}
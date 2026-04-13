package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainRongngawi extends AppCompatActivity {
ListView listngawi;

String [] judul={
        "Korban Perang Dunia I (1914-1918)",
        "Korban Perang Dunia II (1939-1945) ",
        " Perang Punik (264–146 SM) "
};
String [] isi={
        "15 hingga 22 juta kematian (militer dan sipil) dan lebih dari 20 juta orang luka-luka....",
        "korban jiwa diperkirakan mencapai 70 hingga lebih dari 85 juta orang....",
        "Perang Punik (264–146 SM) adalah tiga rangkaian konflik besar antara Republik Romawi dan Kartago (Punisia) yang memperebutkan dominasi di Mediterania Barat..."
};

String [] link={
     "https://id.wikipedia.org/wiki/Perang_Dunia_I",
        "https://id.wikipedia.org/wiki/Perang_Dunia_II",
        "https://id.wikipedia.org/wiki/Perang_Punik"
};
String [] video={
        "https://youtu.be/yFQrh_KK2ws?si=EoiH8XbmxRcXWiCR",
        "https://youtu.be/fuNjaA7RUrE?si=sdUnXOYerAIbQMfh",
         "https://youtu.be/YIA0bcC_BNk?si=hJqQf42FjE7TYROn"
};

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
        listngawi=findViewById(R.id.listngawi);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,judul
        );
        listngawi.setAdapter(adapter);
        listngawi.setOnItemClickListener((parent, view, position, id) -> {


            Intent intent = new Intent(MainRongngawi.this, ngawiActivity.class);

            intent.putExtra("judul", judul[position]);
            intent.putExtra("isi", isi[position]);
            intent.putExtra("link", link[position]);
            intent.putExtra("video", video[position]);

            startActivity(intent);
        });
    }
}
package com.example.listador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listado extends AppCompatActivity {
    ListView listador;
    String elides;
    ArrayList<Peliculafue> pelis = new ArrayList<Peliculafue>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        elides = getResources().getString(R.string.elides);
        listador = findViewById(R.id.listado);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        pelis = (ArrayList<Peliculafue>) bundle.getSerializable("pelis");

        Adaptador adaptador = new Adaptador(this,pelis);

        listador.setAdapter(adaptador);
        listador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(listado.this,elides+" "+id,Toast.LENGTH_SHORT).show();
            }
        });


    }
}

package com.example.listador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonagregar, botonver;
    Spinner cajagenero;
    EditText cuadrotitulo, cuadrofecha, cuadrocali;
    ListView listado;
    ArrayList<Peliculafue> pelis = new ArrayList<Peliculafue>();
    String campovacio, maxmin, numinvalido,error,inforvalida,fechainva,mayorprimera,caliinva;
    String elementoagregado,almenosuno;
    String accion, aventura, comedia, drama, terror, musicales, cienciafic, infantil, suspenso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonagregar = findViewById(R.id.botonagregar);
        botonver = findViewById(R.id.botonver);
        botonagregar.setOnClickListener(this);
        botonver.setOnClickListener(this);

        cajagenero = findViewById(R.id.cajagenero);

        cuadrotitulo = findViewById(R.id.cuadrotitulo);
        cuadrofecha = findViewById(R.id.cuadrofecha);
        cuadrocali = findViewById(R.id.cuadrocali);

        campovacio = getResources().getString(R.string.campovacio);
        maxmin = getResources().getString(R.string.maxmin);
        numinvalido = getResources().getString(R.string.numinvalido);
        error = getResources().getString(R.string.error);
        inforvalida = getResources().getString(R.string.inforvalida);
        fechainva = getResources().getString(R.string.fechainva);
        mayorprimera = getResources().getString(R.string.mayorprimera);
        caliinva = getResources().getString(R.string.caliinva);
        accion = getResources().getString(R.string.accion);
        aventura = getResources().getString(R.string.aventuras);
        comedia = getResources().getString(R.string.comedia);
        drama = getResources().getString(R.string.drama);
        terror = getResources().getString(R.string.terror);
        musicales = getResources().getString(R.string.musicales);
        cienciafic = getResources().getString(R.string.cienciafic);
        infantil = getResources().getString(R.string.infantil);
        suspenso = getResources().getString(R.string.suspenso);
        elementoagregado = getResources().getString(R.string.elementoagregado);
        almenosuno = getResources().getString(R.string.almenosuno);

        String[] opciones = {accion,aventura,cienciafic,comedia,drama,infantil,musicales,suspenso,terror};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_colores, opciones);

        cajagenero.setAdapter(adapter);

        listado = findViewById(R.id.listado);


    }

    @Override
    public void onClick(View v) {

        String auxname, auxfecha, auxcali,auxgenero,  titulopeli, fechapeli,calipeli,generopeli;

        ArrayList<Integer> ids = new ArrayList<Integer>();

        switch (v.getId()){
            case R.id.botonagregar:

                try{
                    auxname =cuadrotitulo.getText().toString();
                    if(validainformacion(auxname)){
                        titulopeli = auxname;
                        cuadrotitulo.setText("");
                    } else {
                        cuadrotitulo.setError(error);
                        Toast.makeText(MainActivity.this,inforvalida,Toast.LENGTH_SHORT).show();
                        break;
                    }

                    auxfecha = cuadrofecha.getText().toString();
                    if(validarfecha(auxfecha)){
                        cuadrofecha.setText("");
                        fechapeli = auxfecha;
                    } else {
                        cuadrofecha.setError(mayorprimera);
                        Toast.makeText(MainActivity.this,fechainva,Toast.LENGTH_SHORT).show();
                        break;
                    }

                    auxcali = cuadrocali.getText().toString();
                    if(validarcali(auxcali)){
                        cuadrocali.setText("");
                        calipeli = auxcali;
                    } else{
                        cuadrocali.setError(maxmin);
                        Toast.makeText(MainActivity.this,caliinva,Toast.LENGTH_SHORT).show();
                        break;
                    }

                    String id = generarID(pelis, ids);

                    auxgenero = cajagenero.getSelectedItem().toString();
                    generopeli = Integer.toString(genero(auxgenero));

                    Peliculafue pelitemp =  new Peliculafue(id,titulopeli,generopeli,fechapeli,calipeli);
                    pelis.add(pelitemp);
                    Toast.makeText(MainActivity.this,String.valueOf(elementoagregado+" "+pelis.size()),Toast.LENGTH_SHORT).show();




                } catch (Exception e){
                    break;
                }
                break;

            case R.id.botonver:
                try{
                    if (listo(pelis.size())) {
                        Toast.makeText(MainActivity.this,almenosuno, Toast.LENGTH_SHORT).show();

                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pelis",pelis);
                        Intent intent = new Intent(MainActivity.this,listado.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                } catch (Exception e){
                    break;
                }

            default:
                break;

            }

    }

    private boolean listo(int tam) {
        if (1 > tam) return true;
        return false;
    }

    private String generarID(ArrayList<Peliculafue> pelis, ArrayList<Integer> ids) {
        int num = pelis.size();
        int ram = (int) Math.floor(Math.random()*1000);
        boolean sta = pelis.contains(num+ram);
        if(sta) generarID( pelis, ids);
        else{
            ids.add(num+ram);
        }
        return Integer.toString(num +ram);
    }

    private boolean validarcali(String auxcali) {
        if(auxcali.equals("")) return false;
        else{
            float cali = Float.parseFloat(auxcali);
            if( cali >= 0.0 && cali <= 10.0 ) return true;
            return false;
        }
    }

    private boolean validarfecha(String auxfecha) {

        if (auxfecha.equals("")) return false;
        else{
            int num = Integer.parseInt(auxfecha);

            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = new Date();
            String fechaa = fecha.format(date);

            int year = Integer.parseInt(fechaa.substring(6,10));

            if (num >= 1895 && num <= year) return true;
            return false;

        }

    }

    private boolean validainformacion(String auxname) {
        boolean vac = true;
        boolean min = true;
        if (auxname.equals("")) vac = false;
        if (auxname.matches(".*[^A-Z ].*")) min = false;
        if (vac && min) return true;
        return false;
    }

    private int genero(String genero){
        int generopeli = 0;

        if (genero.equals(accion)) generopeli = 1;
        if (genero.equals(aventura)) generopeli = 2;
        if (genero.equals(cienciafic)) generopeli = 3;
        if (genero.equals(comedia)) generopeli = 4;
        if (genero.equals(drama)) generopeli = 5;
        if (genero.equals(infantil)) generopeli = 6;
        if (genero.equals(musicales)) generopeli = 7;
        if (genero.equals(suspenso)) generopeli = 8;
        if (genero.equals(terror)) generopeli = 9;

        return generopeli;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
 }




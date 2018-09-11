package com.gustavo.mobdoc_v0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {
    private ImageButton btnControlar;
    private ImageButton btnDados;
    private ImageButton btnExames;
    private ImageButton btnProntuario;
    private Spinner sp;
    FirebaseDatabase database;
    DatabaseReference refSessao;

    String names[] = {"1º Andar - Quarto 101", "1º Andar - Quarto 102", "2º Andar - Quarto 201"};
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnControlar = (ImageButton) findViewById(R.id.btnControlar);
        btnDados = (ImageButton) findViewById(R.id.btnDados);
        btnExames = (ImageButton) findViewById(R.id.btnExames);
        btnProntuario = (ImageButton) findViewById(R.id.btnProntuario);
        database = FirebaseDatabase.getInstance();
        refSessao = database.getReference("quartos/quarto1/sessao");

        sp = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,names);

        sp.setAdapter(adapter);

        btnControlar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    refSessao.setValue("s");
                    startActivity();
                }
                return false;
            }
        });

        btnDados.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    startDados();
               return false;
            }
        });
        btnExames.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    startExames();
                return false;
            }
        });
        btnProntuario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    startProntuario();
                return false;
            }
        });
    }
    private void startActivity(){
        Intent Main = new Intent(this,MainActivity.class);
        startActivity(Main);
    }
    private void startDados(){
        Intent Dados = new Intent(this,DadosActivity.class);
        startActivity(Dados);
    }
    private void startExames(){
        Intent Exames = new Intent(this,ExamesActivity.class);
        startActivity(Exames);
    }
    private void startProntuario(){
        Intent prontuario = new Intent(this,ProntuarioActivity.class);
        startActivity(prontuario);
    }

}



package com.gustavo.mobdoc_v0;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 29/09/2017.
 */

public class ProntuarioActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private List<ProntuarioUpload> pronList;
    private ListView lvPron;
    private ProntuarioListAdapter adapter;
    private ProgressDialog progressDialog;
    private Button btnEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prontuario);
        pronList = new ArrayList<>();
        btnEditar = (Button) findViewById(R.id.btnEditar);
        lvPron = (ListView) findViewById(R.id.listViewProntuario);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor aguarde o carregamento...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pacientes/p001/prontuario");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProntuarioUpload dados = snapshot.getValue(ProntuarioUpload.class);
                    pronList.add(dados);
                }
                //Init adapter
                adapter = new ProntuarioListAdapter(ProntuarioActivity.this, R.layout.activity_prontuario2, pronList);
                //Set adapter for listView
                lvPron.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnEditar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    finish();
                    startEditar();
                return false;
            }
        });


    }
    public void startEditar(){
        Intent editarProntuario = new Intent(this,EditarProntuarioActivity.class);
        startActivity(editarProntuario);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }

}

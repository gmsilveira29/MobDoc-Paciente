package com.gustavo.mobdoc_v0;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DadosActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private List<DadosUpload> dadosList;
    private ListView lvDados;
    private DadosListAdapter adapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        dadosList = new ArrayList<>();
        lvDados = (ListView) findViewById(R.id.listViewDados);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor aguarde o carregamento...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pacientes/p001/Dados");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DadosUpload dados = snapshot.getValue(DadosUpload.class);
                    dadosList.add(dados);
                }
                //Init adapter
                adapter = new DadosListAdapter(DadosActivity.this, R.layout.activity_dados2, dadosList);
                //Set adapter for listView
                lvDados.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}

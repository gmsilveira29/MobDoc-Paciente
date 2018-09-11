package com.gustavo.mobdoc_v0;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class EditarProntuarioActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private String value;
    private int valor;
    private Button btnAnotar;
    private EditText edtAnotar;
    private String texto;
    private String months[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    FirebaseDatabase database;
    DatabaseReference refDesc;
    DatabaseReference refData;
    DatabaseReference refCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_prontuario);
        btnAnotar = (Button) findViewById(R.id.btnAnotar);
        edtAnotar = (EditText) findViewById(R.id.edtAnotar);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pacientes/p001/cont");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                valor = dataSnapshot.getValue(int.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        btnAnotar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    texto = edtAnotar.getText().toString();
                    valor = valor +1;
                    GregorianCalendar gCalendar = new GregorianCalendar();
                    String dia = String.valueOf(gCalendar.get(Calendar.DATE));
                    String mes = String.valueOf(months[gCalendar.get(Calendar.MONTH)]);
                    String ano = String.valueOf(gCalendar.get(Calendar.YEAR));
                    String hora = String.valueOf(gCalendar.get(Calendar.HOUR_OF_DAY));
                    String minuto = String.valueOf(gCalendar.get(Calendar.MINUTE));

                    String data = ("Data: "+dia+"/"+mes+"/"+ano+" - "+hora+":"+minuto);

                    // Write a message to the database
                    database = FirebaseDatabase.getInstance();
                    refDesc = database.getReference("pacientes/p001/prontuario/"+valor+"/desc");
                    refData = database.getReference("pacientes/p001/prontuario/"+valor+"/name");
                    refCont = database.getReference("pacientes/p001/cont");

                    refDesc.setValue(texto);
                    refData.setValue(data);
                    refCont.setValue(valor);
                    startProntuario();
                }


                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
        startProntuario();
    }
    private void startProntuario(){
        Intent prontuario = new Intent(this,ProntuarioActivity.class);
        startActivity(prontuario);
    }

}

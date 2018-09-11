package com.gustavo.mobdoc_v0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText edtNome,edtSenha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getPreferences(MODE_PRIVATE);
        edtNome = (EditText) findViewById(R.id.edtNome);

        edtSenha = (EditText)  findViewById(R.id.edtSenha);

        edtNome.setText(sp.getString("digite_o_nome",""));

        edtSenha.setText(sp.getString("digite_a_senha",""));

    }

    public void login(View view) {
        String digite_o_nome = edtNome.getText().toString();
        String digite_a_senha = edtSenha.getText().toString();

        if(digite_o_nome.equals("haoc1") && digite_a_senha.equals("haoc1")){

            startActivity();
        }
    }

    private void startActivity(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }
}

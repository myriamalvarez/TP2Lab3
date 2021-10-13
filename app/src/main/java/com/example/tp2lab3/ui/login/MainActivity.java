package com.example.tp2lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp2lab3.R;
import com.example.tp2lab3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    Button btLogin, btRegistro;
    EditText etUser, etPass;
    private ViewModelMain vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
    }
    public void inicializarVista(){
        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistrarse);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                i.putExtra("login", false);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.logueo(etUser.getText().toString(), etPass.getText().toString());
            }
        });
    }
}
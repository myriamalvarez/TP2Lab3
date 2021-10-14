package com.example.tp2lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tp2lab3.R;
import com.example.tp2lab3.model.Usuario;
import com.example.tp2lab3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private Button btLogin, btRegistro;
    private EditText etUser, etPass;
    private ViewModelMain vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);
        inicializarVista();
        vm.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                verUsuarioActual();
            }
        });
        vm.getMensajeM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("ALERT").setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }

                        }).show();
            }
        });
    }
    public void inicializarVista(){

        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistrarse);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               vm.registrar();
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.verificar(etUser.getText().toString(), etPass.getText().toString());
            }
        });
    }
    public void verUsuarioActual()
    {
        Intent in = new Intent(this, RegistroActivity.class);
        in.putExtra("login", "usuarioActual");
        startActivity(in);
    }
}
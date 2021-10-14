package com.example.tp2lab3.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tp2lab3.R;
import com.example.tp2lab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText etDni, etApellido, etNombre, etMail, etPass;
    private Button btGuardar;
    private ViewModelRegistro vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializarVista();

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        vm.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText("" + usuario.getDni());
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etMail.setText(usuario.getMail());
                etPass.setText(usuario.getPassword());
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.guardarRegistro(
                        Integer.parseInt(etDni.getText().toString()),
                        etApellido.getText().toString(),
                        etNombre.getText().toString(),
                        etMail.getText().toString(),
                        etPass.getText().toString()
                );
            }
        });
        Bundle datos = this.getIntent().getExtras();
        vm.verUsuarioRegistrado(datos);
    }
    public void inicializarVista() {

        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etMail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btGuardar = findViewById(R.id.btGuardar);
    }
}
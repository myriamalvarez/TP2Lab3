package com.example.tp2lab3.ui.registro;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3.model.Usuario;
import com.example.tp2lab3.request.ApiClient;
import com.example.tp2lab3.ui.login.MainActivity;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioM;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioM() {
        if (usuarioM == null) {
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }
    public void guardarRegistro (long dni, String apellido , String nombre, String email, String password)
    {

        Usuario usuario= new Usuario(dni,apellido,nombre,email,password);
        usuario.setDni(dni);
        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setMail(email);
        usuario.setPassword(password);

        ApiClient.guardar(context, usuario);

        volverLogin();

    }


    public void verUsuarioRegistrado(Bundle d)
    {
        if(d != null)
        {
            Usuario usuario = ApiClient.leer(context);
            usuarioM.setValue(usuario);
        }

    }


    public void volverLogin()
    {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}


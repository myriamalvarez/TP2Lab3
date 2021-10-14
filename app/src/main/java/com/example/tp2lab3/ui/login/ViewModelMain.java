package com.example.tp2lab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3.model.Usuario;
import com.example.tp2lab3.request.ApiClient;
import com.example.tp2lab3.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    Context context;
    MutableLiveData<Usuario> usuarioM;
    MutableLiveData<String> mensajeM;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuarioM(){
        if (usuarioM == null){
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }
    public LiveData<String> getMensajeM(){
        if (mensajeM == null){
            mensajeM = new MutableLiveData<>();
        }
        return mensajeM;
    }
    public void registrar()
    {
        Intent in = new Intent(context, RegistroActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
    }

    public void verificar(String mail, String pass)
    {
        if(mail != null && pass != null)
        {
            Usuario u = ApiClient.login(context, mail, pass);

            if(u != null)
            {
                usuarioM.setValue(u);
            }
            else
            {
                mensajeM.setValue("Datos incorrectos!");
            }

        }
        else
        {
            mensajeM.setValue("Datos incorrectos!");
        }

    }
}

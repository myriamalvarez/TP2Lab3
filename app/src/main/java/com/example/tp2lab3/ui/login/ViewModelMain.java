package com.example.tp2lab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tp2lab3.model.Usuario;
import com.example.tp2lab3.request.ApiClient;
import com.example.tp2lab3.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private ApiClient api;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public void logueo(String email, String pass){
        Usuario res = api.login(context, email, pass);

        if (res != null){
            Intent i = new Intent(context, RegistroActivity.class);
            i.putExtra("login", true);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else{
            Toast.makeText(this.context, "El usuario no existe", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}

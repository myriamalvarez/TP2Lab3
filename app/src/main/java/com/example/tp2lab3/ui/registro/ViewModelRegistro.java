package com.example.tp2lab3.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3.model.Usuario;
import com.example.tp2lab3.request.ApiClient;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private ApiClient api;
    private MutableLiveData<Usuario> registro;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Usuario> getRegistro(){
        if (registro == null){
            registro = new MutableLiveData<>();
        }
        return registro;
    }
    public void guardarDatos(Usuario usuario){
        api.guardar(context, usuario);
        Toast.makeText(this.context, "Usuario guardado con exito", Toast.LENGTH_SHORT).show();
    }
    public void leerDatos(){
        Usuario user = api.leer(context);
        registro.setValue(user);
    }
}

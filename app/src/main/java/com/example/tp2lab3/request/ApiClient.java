package com.example.tp2lab3.request;

import android.content.Context;
import android.widget.Toast;

import com.example.tp2lab3.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

  /*  private static File file;

    private static File conectar(Context context){
        if(file == null){
            file = new File(context.getFilesDir(), "datos.dat");
        }
        return file;
    }*/

    public static void guardar(Context context, Usuario usuario){
        File file = new File(context.getFilesDir(), "usuarios.txt");
        try{
            FileOutputStream fo = new FileOutputStream(file);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream objOutput = new ObjectOutputStream(bo);
            objOutput.writeObject(usuario);
            objOutput.flush();
            objOutput.close();

            Toast.makeText(context, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e){
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
        }
    }
    public static Usuario leer(Context context){
        Usuario user  = null;
        File file = new File(context.getFilesDir(), "usuarios.txt");

        try{
            FileInputStream fi = new FileInputStream(file);
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream objectInput = new ObjectInputStream(bi);
            user = (Usuario) objectInput.readObject();
            objectInput.close();
            return user;
        }catch (FileNotFoundException e){
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
            return user;
        }catch (IOException e){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
            return user;
        }catch (ClassNotFoundException e){
            Toast.makeText(context, "Class not found Exception", Toast.LENGTH_SHORT).show();
            return user;
        }
        //return usuario;
    }
    public static Usuario login(Context context, String email, String pass){
        Usuario logueado = null;
        Usuario user = leer(context);
        long dni = user.getDni();
        String apellido = user.getApellido();
        String nombre = user.getNombre();
        String mail = user.getMail();
        String password = user.getPassword();

            if (email.equals(mail) && pass.equals(password)){
                logueado = new Usuario(dni, apellido, nombre, mail, password);
            }
            return logueado;
    }
}

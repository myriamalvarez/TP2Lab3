package com.example.tp2lab3.request;

import android.content.Context;
import android.widget.Toast;

import com.example.tp2lab3.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {

    private static File file;

    private static File conectar(Context context){
        if(file == null){
            file = new File(context.getFilesDir(), "datos.dat");
        }
        return file;
    }

    public static void guardar(Context context, Usuario usuario){
        try{
            File fi = conectar(context);
            FileOutputStream fo = new FileOutputStream(fi);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            DataOutputStream dos = new DataOutputStream(bo);
            dos.writeLong(usuario.getDni());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getMail());
            dos.writeUTF(usuario.getPassword());

            bo.flush();
            fo.close();
        }catch (FileNotFoundException e){
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
        }
    }
    public static Usuario leer(Context context){
        Usuario usuario = null;

        try{
            File fl = conectar(context);
            FileInputStream fi = new FileInputStream(fl);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);

            long dni = dis.readLong();
            String apellido = dis.readUTF();
            String nombre = dis.readUTF();
            String mail = dis.readUTF();
            String password = dis.readUTF();

            usuario = new Usuario(dni, apellido, nombre, mail, password);
            fi.close();
        }catch (FileNotFoundException e){
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }
    public static Usuario login(Context context, String email, String pass){
        Usuario usuario = null;
        try{
            File fl = conectar(context);
            FileInputStream fi = new FileInputStream(fl);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);

            long dni = dis.readLong();
            String apellido = dis.readUTF();
            String nombre = dis.readUTF();
            String mail = dis.readUTF();
            String password = dis.readUTF();

            if (email.equals(mail) && pass.equals(password)){
                usuario = new Usuario(dni, apellido, nombre, mail, password);
            }
            fi.close();
        }catch (FileNotFoundException e){
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }
}

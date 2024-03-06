package com.example.app_votacion.datos;
import java.io.*;

public class FileManager {
    public static void guardarUrna(Urna urna, String rutaArchivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(rutaArchivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(urna);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Urna cargarUrna(String rutaArchivo) {
        Urna urna = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            urna = (Urna) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return urna;
    }
}


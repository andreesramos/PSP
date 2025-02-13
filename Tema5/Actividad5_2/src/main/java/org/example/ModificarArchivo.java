package org.example;

import java.io.*;

public class ModificarArchivo {
    public static void main(String[] args) {

        try {
            // Leer el contenido original del archivo
            FileInputStream fileIn = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataIn = new ObjectInputStream(fileIn);

            // Leer el String original
            String datosOriginal = (String) dataIn.readObject();

            // Leer el resumen original
            byte[] resumenOriginal = (byte[]) dataIn.readObject();

            dataIn.close();
            fileIn.close();

            // Modificar el contenido original
            String datosModificados = datosOriginal.replace("Mancha", "mancha");

            // Escribir los datos modificados en el archivo
            FileOutputStream fileOut = new FileOutputStream("DATOS.DAT");
            ObjectOutputStream dataOut = new ObjectOutputStream(fileOut);

            dataOut.writeObject(datosModificados); // Escribimos el texto modificado
            dataOut.writeObject(resumenOriginal);  // Mantenemos el original

            dataOut.close();
            fileOut.close();

            System.out.println("Archivo modificado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

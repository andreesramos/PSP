package org.example;

import java.io.*;
import java.security.*;

public class Ejemplo5 {
    public static void main(String[] args) {

        try{
            FileOutputStream fileout = new FileOutputStream("DATOS.DAT");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String datos = "En un lugar de la Mancha, "
                     + "de cuyo nombre no quiero acordarme, no ha mucho tiempo "
                     + "que vivia un hidalgo de los de lanza en astillero, "
                     + "adarga antigua, rocin flaco y galgo corredor.";

            byte dataBytes[] = datos.getBytes();

            md.update(dataBytes); //Texto a resumir
            byte resumen[] = md.digest(); //Se calcula el resumen
            dataOS.writeObject(datos); //Se escriben los datos
            dataOS.writeObject(resumen); //Se escribe el resumen

            dataOS.close();
            fileout.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}
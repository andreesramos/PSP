package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Parte1 {
    public static void main(String[] args) {

        MessageDigest md;

        try{
            md=MessageDigest.getInstance("MD5");
            String texto="Esto es un texto plano";

            byte[] dataBytes = texto.getBytes(); //Texto a bytes
            md.update(dataBytes); //Se introduce texto en bytes a resumir
            byte resumen[] = md.digest(); //Se calcula el resumen

            System.out.println("Mensaje original: " + texto);
            System.out.println("Numero de bytes: " + md.getDigestLength());
            System.out.println("Algoritmo: " + md.getAlgorithm());
            System.out.println("Mensaje de resumen: " + new String(resumen));
            System.out.println("Mensaje en hexadecimal: " + Hexadecimal(resumen));

            Provider proveeedor = md.getProvider();
            System.out.println("Proveedor: " + proveeedor.toString());

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    } //main

    //Convierte un array de bytes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String hex="";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if(h.length()==1){
                hex += "0";
            }
            hex += h;
        }
        return hex.toUpperCase();
    } //Hexadecimal
} //Actividad
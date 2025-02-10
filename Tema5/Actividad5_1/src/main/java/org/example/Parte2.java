package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Parte2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Leer tres cadenas desde el teclado
        System.out.print("Ingrese la primera cadena: ");
        String cadena1 = scanner.nextLine();

        System.out.print("Ingrese la segunda cadena: ");
        String cadena2 = scanner.nextLine();

        System.out.print("Ingrese la clave: ");
        String clave = scanner.nextLine();

        scanner.close();

        // Obtener los resumenes de las dos cadenas combinadas con la clave
        String hash1 = obtenerResumen(cadena1 + clave);
        String hash2 = obtenerResumen(cadena2 + clave);

        // Imprimir resultados
        System.out.println("\nResumen de la primera cadena: " + hash1);
        System.out.println("Resumen de la segunda cadena: " + hash2);

        // Comparar resumenes
        if (hash1.equals(hash2)) {
            System.out.println("\nLos resúmenes son IGUALES.");
        } else {
            System.out.println("\nLos resúmenes son DIFERENTES.");
        }
    }

    // Obtiene el resumen SHA-256 de una cadena
    static String obtenerResumen(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dataBytes = texto.getBytes(); //Texto a bytes
            md.update(dataBytes); //Se introduce texto en bytes a resumir
            byte resumen[] = md.digest();

            return Hexadecimal(resumen);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convierte un array de bytes a hexadecimal
    static String Hexadecimal(byte[] resumen) {
        StringBuilder hex = new StringBuilder();
        for (byte b : resumen) {
            String h = Integer.toHexString(b & 0xFF);
            if (h.length() == 1) {
                hex.append("0");
            }
            hex.append(h);
        }
        return hex.toString().toUpperCase();
    }
}

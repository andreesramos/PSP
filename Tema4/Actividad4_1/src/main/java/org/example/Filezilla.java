package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class Filezilla {
    public static void main(String[] args) {

        FTPClient cliente = new FTPClient();
        String servFTP = "localhost";
        System.out.println("Nos conectamos a: " + servFTP);
        String usuario = "Andres";
        String clave = "andres";


        try{
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode();

            boolean login = cliente.login(usuario, clave);
            if(login){
                System.out.println("Login correcto...");
            }else{
                System.out.println("Login incorrecto...");
                cliente.disconnect();
                System.exit(1);
            }
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
            FTPFile[] files = cliente.listFiles("/Andres");
            System.out.println("Ficheros en el directorio actual: " + files.length);
            String tipos[] = {"Fichero", "Directorio", "Enlace simb."};

            for(int i=0; i<files.length; i++){
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }

            cliente.disconnect();
            System.out.println("Desconectado...");

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a: " + servFTP);
        String usuario = "anonymous";
        String clave = "anonymous";

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
            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual: " + files.length);
            String tipos[] = {"Fichero", "Directorio", "Enlace simb."};

            for(int i=0; i<files.length; i++){
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }

            //cambiar a /mirror/MySQL/Downloads/
            String directorio = "/mirror/MySQL/Downloads/";
            if(cliente.changeWorkingDirectory(directorio)) {
                System.out.println("Dir Actual: " + cliente.printWorkingDirectory());

            }
            else{
                System.out.println("NO EXISTE EL DIRECTORIO: " + directorio);
            }
            FTPFile[] files2 = cliente.listFiles();
            for(int j=0; j<files.length; j++){
                System.out.println("\t" + files2[j].getName() + " => " + tipos[files2[j].getType()]);
            }

            //cambiar a /debian
            String directorio2 = "/debian";
            if(cliente.changeWorkingDirectory(directorio2)) {
                System.out.println("Dir Actual: " + cliente.printWorkingDirectory());

            }
            else{
                System.out.println("NO EXISTE EL DIRECTORIO: " + directorio2);
            }
            FTPFile[] files3 = cliente.listFiles();
            for(int j=0; j<files.length; j++){
                System.out.println("\t" + files3[j].getName() + " => " + tipos[files3[j].getType()]);
            }

            boolean logout = cliente.logout();
            if(logout){
                System.out.println("Logout del servidor FTP...");
            }
            else{
                System.out.println("Error al hacer Logout...");
            }
            cliente.disconnect();
            System.out.println("Desconectado...");

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
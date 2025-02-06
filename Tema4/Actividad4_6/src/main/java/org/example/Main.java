package org.example;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String usuario, clave;

        // Bucle de autenticacion hasta que el usuario decida salir
        while (true) {
            System.out.print("Introduce el nombre de usuario (* para salir): ");
            usuario = scanner.nextLine();

            if (usuario.equals("*")) {
                System.out.println("Saliendo del sistema...");
                break;
            }

            System.out.print("Introduce la clave: ");
            clave = scanner.nextLine();

            // Verificacion de credenciales en el servidor FTP
            if (autenticarUsuarioFTP(usuario, clave)) {
                System.out.println("Autenticación exitosa.");
                registrarConexion(usuario, clave);
            } else {
                System.out.println("Credenciales incorrectas. Se enviará una alerta al administrador.");
                alerta(usuario);
            }
        }
        scanner.close();
    }

    //Verifica las credenciales de un usuario en el servidor FTP
    private static boolean autenticarUsuarioFTP(String usuario, String clave) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("localhost");
            boolean autenticado = ftpClient.login(usuario, clave);
            ftpClient.logout();
            ftpClient.disconnect();
            return autenticado;
        } catch (IOException e) {
            System.err.println("Error al autenticar en el servidor FTP: " + e.getMessage());
            return false;
        }
    }

    // Registra la conexion de un usuario exitoso en un archivo de LOG dentro del servidor FTP
    private static void registrarConexion(String usuario, String clave) {
        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect("localhost"); // Conectar al servidor FTP
            if (ftpClient.login(usuario, clave)) {
                ftpClient.enterLocalPassiveMode(); // Configurar modo pasivo
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // Establecer el tipo de archivo binario

                // Directorio de LOG para el usuario
                String rutaLog = "/" + usuario + "/LOG";
                if (!ftpClient.changeWorkingDirectory(rutaLog)) {
                    ftpClient.makeDirectory(rutaLog); // Crear directorio si no existe
                    ftpClient.changeWorkingDirectory(rutaLog);
                }

                // Formato de la fecha y contenido del LOG
                String archivoLog = "LOG.TXT";
                String marcaDeTiempo = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).format(new Date());
                String contenidoLog = "Conexión registrada para el usuario:\nHora de conexión: " + marcaDeTiempo + "\n\n";

                // Subir o actualizar el archivo de LOG en el servidor FTP
                InputStream inputStream = new ByteArrayInputStream(contenidoLog.getBytes());
                ftpClient.storeFile(archivoLog, inputStream);
                inputStream.close();

                System.out.println("Conexión registrada en " + rutaLog + "/" + archivoLog);
            } else {
                System.out.println("No se pudo autenticar en el servidor FTP.");
            }
            ftpClient.logout();
        } catch (IOException e) {
            System.err.println("Error al registrar la conexión: " + e.getMessage());
        } finally {
            try {
                ftpClient.disconnect(); // Desconectar del servidor
            } catch (IOException ex) {
                System.err.println("Error al desconectar del servidor FTP: " + ex.getMessage());
            }
        }
    }

    //Simula el envío de una alerta al administrador en caso de intento de inicio de sesión fallido
    private static void alerta(String usuario) {
        System.out.println("[SIMULACIÓN] Correo de alerta enviado al administrador: \"Usuario " + usuario +
                " intentó iniciar sesión con credenciales incorrectas.\"");
    }
}

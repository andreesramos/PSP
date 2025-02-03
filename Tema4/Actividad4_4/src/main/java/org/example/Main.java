package org.example;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import org.apache.commons.net.smtp.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos por teclado
        System.out.print("Introduce servidor SMTP: ");
        String server = scanner.nextLine();

        System.out.print("Introduce el puerto: ");
        int puerto = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Necesita negociación TLS (S/N)? ");
        boolean necesitaTLS = scanner.nextLine().equalsIgnoreCase("S");

        System.out.print("Introduce usuario: ");
        String username = scanner.nextLine();

        System.out.print("Introduce password: ");
        String password = scanner.nextLine();

        System.out.print("Introduce correo del remitente: ");
        String remitente = scanner.nextLine();

        System.out.print("Introduce correo del destinatario: ");
        String destinatario = scanner.nextLine();

        System.out.print("Introduce asunto: ");
        String asunto = scanner.nextLine();

        System.out.println("Introduce el mensaje (finaliza con *): ");
        StringBuilder mensaje = new StringBuilder();
        String linea;
        while (!(linea = scanner.nextLine()).equals("*")) {
            mensaje.append(linea).append("\n");
        }

        if (mensaje.toString().trim().isEmpty()) {
            System.out.println("No se puede enviar un mensaje vacío.");
            System.exit(1);
        }

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            client.connect(server, puerto);
            System.out.println("Conectado al servidor SMTP.");

            client.setKeyManager(km);
            if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();
                System.err.println("Conexión rechazada.");
                System.exit(1);
            }

            client.ehlo(server);
            System.out.println("EHLO enviado.");

            if (necesitaTLS && client.execTLS()) {
                System.out.println("Negociación TLS establecida.");
            } else if (necesitaTLS) {
                System.err.println("Fallo en la negociación TLS.");
                System.exit(1);
            }

            if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                System.out.println("Autenticación exitosa.");

                SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);
                client.setSender(remitente);
                client.addRecipient(destinatario);

                Writer writer = client.sendMessageData();
                if (writer == null) {
                    System.err.println("Fallo al enviar datos del mensaje.");
                    System.exit(1);
                }

                writer.write(cabecera.toString());
                writer.write(mensaje.toString());
                writer.close();

                if (client.completePendingCommand()) {
                    System.out.println("Mensaje enviado con éxito.");
                } else {
                    System.err.println("Fallo al completar la transacción.");
                }
            } else {
                System.err.println("Autenticación fallida.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Fin del proceso.");
        }
    }
}

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
        String mensaje = scanner.nextLine();

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString());

            client.setKeyManager(km);

            if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA");
                System.exit(1);
            }

            client.ehlo(server);
            System.out.println("2 - " + client.getReplyString());

            if (client.execTLS()) {
                System.out.println("3 - " + client.getReplyString());

                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());

                    SimpleSMTPHeader header = new SimpleSMTPHeader(remitente, destinatario, asunto);

                    client.setSender(remitente);
                    client.addRecipient(destinatario);
                    System.out.println("5 - " + client.getReplyString());

                    Writer writer = client.sendMessageData();

                    if (writer == null) {
                        System.out.println("FALLO AL ENVIAR");
                        System.exit(1);
                    }

                    writer.write(header.toString());
                    writer.write(mensaje);
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());

                    if (!exito) {
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON ÉXITO.");
                    }
                } else {
                    System.out.println("USUARIO NO AUTENTICADO");
                }
            } else {
                System.out.println("FALLO AL EJECUTAR STARTTLS.");
            }
        } catch (Exception e) {
            System.out.println("No se pudo conectar al servidor.");
            e.printStackTrace();
        } finally {
            try{
                client.disconnect();
                System.out.println("Fin del envío.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

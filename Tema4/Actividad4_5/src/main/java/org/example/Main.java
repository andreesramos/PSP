package org.example;

import java.io.IOException;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Main {
    public static void main(String[] args) {
        // Configuracion del servidor de correo
        String server = "pop.gmail.com", username = "andresrn16@gmail.com", password = "hyfn oymk ffre sdmu";
        int puerto = 995;

        // Se crea un cliente POP3 seguro
        POP3SClient pop3 = new POP3SClient(true);
        try {
            // Se establece conexion con el servidor
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada con el servidor POP3 " + server);

            // Intento de inicio de sesion
            if (!pop3.login(username, password)) {
                System.out.println("Error al hacer login");
            } else {
                // Obtener lista de mensajes disponibles
                POP3MessageInfo[] men = pop3.listMessages();

                if (men == null)
                    System.out.println("No se puede listar los mensajes"); // No se pueden obtener los mensajes
                else {
                    System.out.println("Número de mensajes: " + men.length);
                    mensajes(men, pop3); // Llamada al metodo que procesa los mensajes
                }
                pop3.logout(); // Cierra la sesion en el servidor
            }
            pop3.disconnect(); // Desconecta del servidor
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }


    //Metodo para mostrar informacion de los mensajes en el servidor
    private static void mensajes(POP3MessageInfo[] men, POP3SClient pop3) throws IOException {
        for (int i = 0; i < men.length; i++) {
            System.out.println("\nMensaje " + (i + 1));
            POP3MessageInfo msgInfo = men[i];

            // Se imprimen detalles del mensaje
            System.out.println("ID: " + msgInfo.identifier);
            System.out.println("Número: " + msgInfo.number);
            System.out.println("Tamaño: " + msgInfo.size + " bytes");

            // Obtencion del identificador unico del mensaje
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
            if (pmi != null) {
                System.out.println("Identificador único: " + pmi.identifier);
            } else {
                System.out.println("No se pudo obtener el identificador único.");
            }
        }
    }
}

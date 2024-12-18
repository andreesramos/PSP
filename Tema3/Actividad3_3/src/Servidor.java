import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto=6000;

        try{
            //ServerSocket enlazado al puerto
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Esperando al cliente...");
            Socket cliente = servidor.accept(); //Aceptar cliente

            OutputStream salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeUTF("MENSAJE ENVIADO");//Mandamos mensaje

            //Recogemos el mensaje recibido y lo mostramos
            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            System.out.println("Mensaje recibido del cliente: " +  flujoEntrada.readUTF());

            flujoSalida.close();
            flujoEntrada.close();
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

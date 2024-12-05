import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto=6000;

        try{
            //Creacion serverSocket
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Esperando al cliente...");
            Socket cliente = servidor.accept(); //Aceptamos al cliente

            //Leemos el mensaje recibido del cliente
            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            int num=Integer.parseInt(flujoEntrada.readUTF());
            System.out.println("Mensaje recibido del cliente: " +  num);

            //Mandamos el cuadrado del numero recibido al cliente
            OutputStream salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeUTF(String.valueOf(num*num));

            flujoSalida.close();
            flujoEntrada.close();
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

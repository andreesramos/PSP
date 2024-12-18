import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto=6000;

        try{
            Socket cliente = new Socket(host,puerto);//Creamos el socket

            //Leemos el mensaje del servidor y lo mostramos
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String msg=flujoEntrada.readUTF();
            System.out.println("Mensaje recibido del servidor: " + msg);

            flujoEntrada.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
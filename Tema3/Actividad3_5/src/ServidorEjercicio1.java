import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEjercicio1 {
    public static void main(String[] args) {
        int puerto = 6000;

        try{
            ServerSocket servidor = new ServerSocket(puerto);//Creacion del serverSocket
            System.out.println("Esperando al cliente...");
            Socket cliente = servidor.accept(); //Aceptamos al cliente

            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            //Leemos la cadena pasada del cliente
            String cadena = flujoEntrada.readUTF();

            while(!cadena.equals("*")){ //Si no es igual a *
                flujoSalida.writeUTF(String.valueOf(cadena.length()));//Le enviamos al cliente el numero de caracteres de la cadena

                cadena = flujoEntrada.readUTF(); //Volvemos a leer
            }

            System.out.println("Conexion cerrada por el cliente");

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
            servidor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

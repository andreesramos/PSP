import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host="localhost";
        int puerto=6000;

        try {
            //Creacion de socket con nombre de host y puerto especificados
            Socket cliente = new Socket(host, puerto);

            //Mostramos el puerto local y remoto y la direccion IP de la maquina remota a la que esta
            //conectada el socket
            InetAddress ip = cliente.getInetAddress();
            System.out.println("Puerto Local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("IP Host Remoto: " + ip.getHostAddress());

            cliente.close();//Cerramos el socket
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

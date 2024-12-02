import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto=6000;
        int conectados=0; //Contador para los clientes conectados
        try{
            //Creacion del serverSocket enlazado al puerto
            ServerSocket servidor=new ServerSocket(puerto);

            //Mientras que no se acepten dos clientes
            while(conectados<2) {
                //Acepamos cliente y mostramos su puerto local y remoto
                System.out.println("Esperando cliente...");
                Socket cliente1 = servidor.accept();
                System.out.println("Puerto local: " + cliente1.getLocalPort());
                System.out.println("Puerto remoto: " + cliente1.getPort());
                conectados++; //Incrementamos el contador
            }

            servidor.close(); //Cerramos el serverSocket
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(6001);
            System.out.println("Servidor iniciado...");

            //Numero a adivinar entre 1 y 25
            int numero=(int) (1+25 * Math.random());
            System.out.println("NUMERO A ADIVINAR => "+numero);

            //Todos los hilos comparten el objeto
            ObjetoCompartido objeto = new ObjetoCompartido(numero);
            int id=0;
            while(true){
                Socket cliente = new Socket();
                cliente = servidor.accept();
                id++; //identificador para el cliente
                HiloServidorAdivina hilo=new HiloServidorAdivina(cliente, objeto, id);
                hilo.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 44444;
        try (ServerSocket servidor = new ServerSocket(puerto)) { //Creacion del ServerSocket
            System.out.println("SERVIDOR INICIADO...");

            while (true) {
                try {
                    Socket cliente = servidor.accept(); //Aceptar conexión del cliente
                    System.out.println("=>Conecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
                    HiloServidor hilo = new HiloServidor(cliente);
                    hilo.start(); //Lanzar un hilo para manejar al cliente
                } catch (IOException e) {
                    System.out.println("Error al aceptar cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
    private Socket socket;
    private BufferedReader fentrada;
    private PrintWriter fsalida;

    //Constructor que inializa hilo y flujos
    public HiloServidor(Socket s) {
        try {
            this.socket = s;
            this.fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.fsalida = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String cadena = "";
        try {
            while (!cadena.trim().equals("*")) {
                cadena = fentrada.readLine(); //Lee cadena enviada por el cliente
                fsalida.println(cadena.toUpperCase()); //Enviar cadena en mayúsculas
                System.out.println("=>Conecta IP " + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());
            }
            System.out.println("=>Desconecta IP " + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());

            fentrada.close();
            fsalida.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

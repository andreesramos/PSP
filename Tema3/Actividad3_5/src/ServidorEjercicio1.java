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
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Esperando al cliente...");
            Socket cliente = servidor.accept(); //Aceptamos al cliente

            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);
            String cadena = flujoEntrada.readUTF();

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            while(!cadena.equals("*")){
                flujoSalida.writeUTF(String.valueOf(cadena.length()));

                cadena = flujoEntrada.readUTF();
                //Peta al introducir *
            }

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

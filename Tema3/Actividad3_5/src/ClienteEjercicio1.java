import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEjercicio1 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try{
            Socket cliente = new Socket(host, puerto);

            System.out.println("Introduce una cadena:");
            String cadena=new Scanner(System.in).nextLine();
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            while(!cadena.equals("*")){
                flujoSalida.writeUTF(cadena);

                String msg=flujoEntrada.readUTF();
                System.out.println("Mensaje recibido(numero de caracteres): "+msg);

                System.out.println("Introduce una cadena:");
                cadena=new Scanner(System.in).nextLine();
            }

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try{
            Socket cliente = new Socket(host, puerto); //Creacion del socket

            DataInputStream flujoEntrada= new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            String cadena="";
            while(!cadena.equals("*")){ //Mientras la cadena no sea igual a *
                System.out.println("Introduce una cadena");
                cadena = new Scanner(System.in).nextLine();

                flujoSalida.writeUTF(cadena);

                if(!cadena.equals("*")){ //Leemos el mesaje del servidor y lo mostramos
                    String msg=flujoEntrada.readUTF();
                    System.out.println("Mensaje recibido: "+msg);
                }
            }

            System.out.println("Conexion cerrada");

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

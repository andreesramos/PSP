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
            Socket cliente = new Socket(host, puerto);//Creacion del socket

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

            String cadena="";
            while(!cadena.equals("*")){//Mientras la cadena no sea igual a *
                System.out.println("Introduce una cadena:");
                cadena=new Scanner(System.in).nextLine();

                flujoSalida.writeUTF(cadena);//Le pasamos la cadena al servidor

                if(!cadena.equals("*")){ //Leemos el mesaje del servidor y lo mostramos
                    String msg=flujoEntrada.readUTF();
                    System.out.println("Mensaje recibido(numero de caracteres): "+msg);
                }
            }

            System.out.println("Conexion cerrada");

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

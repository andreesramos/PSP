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
            System.out.println("Programa cliente iniciado...");
            Socket cliente = new Socket(host, puerto); //Creacion del socket

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            System.out.println("Introduce un numero: "); //Pedimos un numero por teclado
            int num=new Scanner(System.in).nextInt();
            flujoSalida.writeUTF(String.valueOf(num)); //Mandamos el numero al servidor

            //Leemos el mensaje recibido y lo mostramos
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String msg=flujoEntrada.readUTF();
            System.out.println("Mensaje recibido del servidor: " + msg);

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
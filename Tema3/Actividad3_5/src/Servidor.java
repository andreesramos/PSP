import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;
        int cont=1;//Contador para los clientes

        //Pedimos el numero de clientes por teclado
        System.out.println("Introduce el numero de clientes que el servidor puede atender");
        int num=new Scanner(System.in).nextInt();

        try{
            //Creacion del serverSocket
            ServerSocket servidor = new ServerSocket(puerto);

            //Mientras el contador sea menor o igual que el numero de clientes
            while(cont<=num){
                System.out.println("Esperando al cliente...");
                Socket cliente = servidor.accept();//Aceptamos cliente

                //Mandamos al cliente el mensaje con su numero de cliente
                OutputStream salida = cliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(salida);
                flujoSalida.writeUTF("Eres el cliente numero " + cont);//Mandamos mensaje
                cont++;//Aumentamos el contador

                flujoSalida.close();
            }
            servidor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

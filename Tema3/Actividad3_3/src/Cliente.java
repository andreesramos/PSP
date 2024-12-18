import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try{
            System.out.println("Programa cliente iniciado...");
            Socket cliente = new Socket(host, puerto); //Creacion socket

            //Leemos el mensaje enviado por el servidor y lo mostramos
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String msg=flujoEntrada.readUTF();
            System.out.println("Mensaje recibido del servidor: " + msg);

            //Mandamos el mensaje recibido al servidor pero en minusculas
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(msg.toLowerCase());

            flujoSalida.close();
            flujoEntrada.close();
            cliente.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

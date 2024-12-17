import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteActividad3_7 {
    public static void main(String[] args) {
        String host="localhost";
        int puerto=6000;

        try{
            //Creación del socket UDP
            DatagramSocket cliente = new DatagramSocket();

            int num=1;
            while(num>0){ //Mientras que sea mayor que 0
                System.out.println("Introduce un numero: ");
                num=new Scanner(System.in).nextInt(); //Leemos numero por teclado


                //Codificacion del objeto Numeros
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ObjectOutputStream salida = new ObjectOutputStream(bs);
                Numeros numeros=new Numeros(); //Creamos un objeto Numeros vacio
                numeros.setNumero(num); //Asignamos el numero leido por teclado al atributo numero
                salida.writeObject(numeros); //Le pasamos el objeto al servidor
                byte[] bytes = bs.toByteArray();

                //Creación y envío del paquete UDP al servidor
                InetAddress ip = InetAddress.getByName(host); // Obtiene la dirección IP del host
                DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ip, puerto);
                cliente.send(paqueteEnviado); // Envía el paquete al servidor




                if(num>0){
                    byte[] recibidos = new byte[1024];
                    DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
                    cliente.receive(pagRecibido);

                    ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
                    ObjectInputStream entrada = new ObjectInputStream(bais);

                    numeros=(Numeros) entrada.readObject(); //Reconstrucción del objeto Numeros desde los bytes
                    //Imprimimos el cuadrado y el cubo
                    System.out.println("Cuadrado: "+numeros.getCuadrado());
                    System.out.println("Cubo: "+numeros.getCubo());
                }

            }

            System.out.println("Conexion cerrada");

            cliente.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

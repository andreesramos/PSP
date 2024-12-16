import java.io.*;
import java.net.*;

public class ServidorActividad3_7 {
    public static void main(String[] args) {
        int puerto=6000;

        try{
            //Creación del socket UDP
            DatagramSocket servidor = new DatagramSocket(puerto);
            byte[] recibidos = new byte[1024]; //Almacenar datos recibidos
            DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
            System.out.println("Esperando al cliente...");

            //Recibe un paquete UDP del cliente
            servidor.receive(pagRecibido);

            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream entrada = new ObjectInputStream(bais);
            Numeros numeros = (Numeros) entrada.readObject();

            int numeroRecibido=numeros.getNumero(); //Asignamos el atributo numero a una variable

            //Codificacion del objeto modificado
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(bs);

            while (numeroRecibido>0){ //Mientras que sea mayor que 0
                //Calculamos cuadrado y cubo
                numeros.setCuadrado((long)numeroRecibido*numeroRecibido);
                numeros.setCubo((long)numeroRecibido*numeroRecibido*numeroRecibido);

                //Mandamos al cliente el objeto
                salida.writeObject(numeros);
                byte[] bytes = bs.toByteArray();

                //Obtiene la dirección IP y puerto del cliente
                InetAddress ip = pagRecibido.getAddress();
                int puertoRecibido = pagRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ip, puertoRecibido);
                servidor.send(paqueteEnviado); //Envía el paquete al cliente

                numeros=(Numeros) entrada.readObject(); //Volvemos a leer el objeto pasado por el cliente
                numeroRecibido=numeros.getNumero();
            }

            System.out.println("Conexion cerrada por el cliente");

            entrada.close();
            salida.close();
            servidor.close();

        }catch(ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}

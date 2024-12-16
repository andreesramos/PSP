import java.io.*;
import java.net.*;

public class ServidorActividad3_8 {
    public static void main(String[] args) {

        int puerto = 6000;

        try {
            //Creación del socket UDP
            DatagramSocket servidor = new DatagramSocket(puerto);
            byte[] recibidos = new byte[1024]; //Almacenar datos recibidos
            DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
            System.out.println("Esperando al cliente...");

            //Recibe un paquete UDP del cliente
            servidor.receive(pagRecibido);

            //Decodificacion del objeto recibido desde el cliente
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bais);
            Persona persona = (Persona) in.readObject();
            System.out.println("Persona recibida: Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());

            //Modificación del objeto recibido
            persona.setEdad(25);
            persona.setNombre("Paco");
            System.out.println("Persona enviada: Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());

            //Cdificacion del objeto modificado
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.writeObject(persona);
            byte[] bytes = bs.toByteArray();

            //Obtiene la dirección IP y puerto del cliente
            InetAddress ip = pagRecibido.getAddress();
            int puertoRecibido = pagRecibido.getPort();
            DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ip, puertoRecibido);
            servidor.send(paqueteEnviado); //Envía el paquete de vuelta al cliente

            System.out.println("Conexion cerrada por el cliente");
            in.close();
            servidor.close();
            out.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

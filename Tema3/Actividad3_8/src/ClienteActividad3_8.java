import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteActividad3_8 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try {
            //Creación del socket UDP
            DatagramSocket cliente = new DatagramSocket();

            //Crear objeto persona y mostrar sus atributos
            Persona persona = new Persona("Andres", 21);
            System.out.println("Persona enviada: Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());

            //Codificacion del objeto Persona
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bs);
            out.writeObject(persona); // Convierte el objeto a bytes
            byte[] bytes = bs.toByteArray();

            //Creación y envío del paquete UDP al servidor
            InetAddress ip = InetAddress.getByName(host); // Obtiene la dirección IP del host
            DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ip, puerto);
            cliente.send(paqueteEnviado); // Envía el paquete al servidor

            //Preparación para recibir la respuesta del servidor
            byte[] recibidos = new byte[1024];
            DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
            cliente.receive(pagRecibido);

            //Decodificación del objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream in = new ObjectInputStream(bais);
            persona = (Persona) in.readObject(); //Reconstrucción del objeto Persona desde los bytes
            System.out.println("Persona recibida: Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());

            out.close();
            in.close();
            cliente.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}


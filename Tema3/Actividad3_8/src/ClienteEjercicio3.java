import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteEjercicio3 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try{
            DatagramSocket cliente = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduce el ID del alumno: ");
            String idAlumno = scanner.nextLine();

            while(!idAlumno.equals("*")){
                // Enviar el ID al servidor
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bs);
                out.writeObject(idAlumno);
                byte[] bytes = bs.toByteArray();

                InetAddress ip = InetAddress.getByName(host);
                DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ip, puerto);
                cliente.send(paqueteEnviado);

                // Recibir la respuesta del servidor
                byte[] recibidos = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
                cliente.receive(paqueteRecibido);

                // Decodificar el objeto Alumno
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream in = new ObjectInputStream(bais);
                Alumno alumno = (Alumno) in.readObject();

                // Mostrar los datos del alumno
                System.out.println("Datos del alumno: " + alumno);

                System.out.print("Introduce el ID del alumno: ");
                idAlumno = scanner.nextLine();
            }

            System.out.println("Conexión cerrada.");
            cliente.close();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

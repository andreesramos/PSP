import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServidorEjercicio3 {
    public static void main(String[] args) {
        int puerto = 6000;

        Curso curso1 = new Curso("C1", "Matemáticas");
        Curso curso2 = new Curso("C2", "Física");
        Curso curso3 = new Curso("C3", "Química");

        Alumno alumno1 = new Alumno("1", "Pepe", curso1, 9);
        Alumno alumno2 = new Alumno("2", "Ana", curso2, 7);
        Alumno alumno3 = new Alumno("3", "Carlos", curso3, 6);
        Alumno alumno4 = new Alumno("4", "Juan", curso1, 8);
        Alumno alumno5 = new Alumno("5", "Pedro", curso2, 4);

        Alumno[] alumnos = {alumno1, alumno2, alumno3, alumno4, alumno5};

        try{
            DatagramSocket servidor = new DatagramSocket(puerto);
            System.out.println("Esperando cliente...");

            while(true){
                // Recibir paquete del cliente
                byte[] recibidos = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
                servidor.receive(paqueteRecibido);

                // Decodificación del ID recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream in = new ObjectInputStream(bais);
                String idAlumno = (String) in.readObject();
                System.out.println("Consulta para alumno con ID: " + idAlumno);

                // Buscar alumno en la lista
                Alumno alumnoEncontrado = null;
                for (Alumno alumno : alumnos ) {
                    if(alumno.getIdAlumno().equals(idAlumno)){
                        alumnoEncontrado = alumno;
                        break;
                    }
                }

                // Si no se encuentra, se crea un alumno "no existe"
                if (alumnoEncontrado == null) {
                    alumnoEncontrado = new Alumno(idAlumno);
                }

                // Codificación del objeto Alumno
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bs);
                out.writeObject(alumnoEncontrado);
                byte[] bytes = bs.toByteArray();

                // Enviar respuesta al cliente
                InetAddress ipCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(bytes, bytes.length, ipCliente, puertoCliente);
                servidor.send(paqueteEnviado);

                System.out.println("Respuesta enviada: " + alumnoEncontrado);
            }

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

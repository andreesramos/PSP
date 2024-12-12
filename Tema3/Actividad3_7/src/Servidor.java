import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto=6000;

        try{
            ServerSocket servidor = new ServerSocket(puerto);//Creacion del serverSocket
            System.out.println("Esperando al cliente...");
            Socket cliente = servidor.accept(); //Aceptamos al cliente

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

            Numeros numeros=(Numeros) entrada.readObject(); //Leemos el objeto Numeros
            int numeroRecibido=numeros.getNumero(); //Asignamos el atributo numero a una variable

            while (numeroRecibido>0){ //Mientras que sea mayor que 0
                //Calculamos cuadrado y cubo
                numeros.setCuadrado((long)numeroRecibido*numeroRecibido);
                numeros.setCubo((long)numeroRecibido*numeroRecibido*numeroRecibido);

                salida.writeObject(numeros); //Le mandamos al cliente el objeto con todos los atributos
                numeros=(Numeros) entrada.readObject(); //Volvemos a leer el objeto pasado por el cliente
                numeroRecibido=numeros.getNumero();
            }

            System.out.println("Conexion cerrada por el cliente");

            entrada.close();
            salida.close();
            cliente.close();
            servidor.close();

        }catch(ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}

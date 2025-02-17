package Ejercicio2;

/*Este es el cliente. Al principio mostrara el id que le ha asignado el servidor. Despues escribira por teclado
* el id de un profesor hasta escribir un *, el servidor le devolvera los profesores correspondientes a esos id.
* No funciona, no recibe el primer mensaje de la asignacion del id. No me ha dado tiempo de terminarlo*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEjercicio2 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto=6000;
        Scanner sc = new Scanner(System.in);

        try{
            Socket cliente = new Socket(host,puerto);//Creamos el socket

            //Leemos el mensaje del servidor y lo mostramos
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String msg=flujoEntrada.readUTF();
            System.out.println("Mensaje recibido del servidor: " + msg);

            System.out.println("Id del profesor a obtener: ");
            int idprofesor = sc.nextInt();

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF("idprofesor");

            //Mostramos el profesor recibido
            ObjectInputStream objetoEntrada = new ObjectInputStream(cliente.getInputStream());
            Profesor prof=(Profesor)objetoEntrada.readObject();
            System.out.println("Profesor recibido del servidor: " + prof.toString());

            flujoEntrada.close();
            cliente.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host="localhost";
        int puerto=6000;

        try{
            Socket cliente=new Socket(host,puerto); //Creacion del socket

            ObjectOutputStream salida=new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada=new ObjectInputStream(cliente.getInputStream());

            int num=1;
            while(num>0){ //Mientras que sea mayor que 0
                System.out.println("Introduce un numero: ");
                num=new Scanner(System.in).nextInt(); //Leemos numero por teclado

                Numeros numeros=new Numeros(); //Creamos un objeto Numeros vacio
                numeros.setNumero(num); //Asignamos el numero leido por teclado al atributo numero
                salida.writeObject(numeros); //Le pasamos el objeto al servidor

                if(num>0){
                    numeros=(Numeros) entrada.readObject(); //Leemos el objeto pasado por el servidor
                    //Imprimimos el cuadrado y el cubo
                    System.out.println("Cuadrado: "+numeros.getCuadrado());
                    System.out.println("Cubo: "+numeros.getCubo());
                }

            }

            System.out.println("Conexion cerrada");

            salida.close();
            entrada.close();
            cliente.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

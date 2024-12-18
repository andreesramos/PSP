import java.net.InetAddress;
import java.util.Scanner;

public class Actividad3_1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un nombre de maquina o una direccion IP");

        try{
            //Obtenemos por teclado una direccion IP o el nombre de una maquina
            InetAddress dir=InetAddress.getByName(scanner.nextLine());
            //Mostramos por pantalla la informacion del dato recibido mediante varios metodos
            System.out.println("Metodo getByName() " + dir);
            System.out.println("Metodo getLocalHost() " + InetAddress.getLocalHost());
            System.out.println("Metodo getHostname() " + dir.getHostName());
            System.out.println("Metodo getHostAdress() " + dir.getHostAddress());
            System.out.println("Metodo toString() " + dir.toString());
            System.out.println("Metodo getCanonicalHostName() " + dir.getCanonicalHostName());
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
import java.io.IOException;
import java.util.Arrays;

/*Recibe un nombre por los argumentos del main y lo visualiza por pantalla*/

public class LeerNombre {

    public static void main(String[] args) throws IOException {
        if(args.length <= 0){
            System.err.println("Se necesitan argumentos en el main");
            System.exit(-1);
        }else{
            System.out.printf("Nombre: %s %n", args[0]);
            System.exit(1);
        }

    }
}

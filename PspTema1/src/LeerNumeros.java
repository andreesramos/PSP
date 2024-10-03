import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*Recibe dos numeros por teclado, comprueba que sean numeros y muestra su suma*/

public class LeerNumeros {
    public static void main(String[] args) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        int num1=0, num2=0;

        try{
            System.out.println("Introduce un numero: ");
            num1= Integer.parseInt(br.readLine());
            System.out.println("Numero introducido: " + num1);
            System.out.println("Introduce otro: ");
            num2=Integer.parseInt(br.readLine());
            System.out.println("Numero introducido: " + num2);
            int suma=num1+num2;
            System.out.println("Suma: " + suma);
            in.close();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }




    }
}

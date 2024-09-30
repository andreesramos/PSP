import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LeerNumeros {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1=0, num2=0, res;

        try{
            System.out.println("Introduce un numero: ");
            num1=sc.nextInt();
            System.out.println("Introduce otro: ");
            num2=sc.nextInt();

        }catch(InputMismatchException e){
            e.printStackTrace();
        }

        res=num1+num2;
        System.out.println(res);
    }
}

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/*Muestra los errores al intentar ejecutar un programa inexistente*/

public class Actividad1_2 {
    public static void main(String[] args) throws IOException {
        File directorio = new File("/home/usuario/PSP/PspTema1/out/production");
        ProcessBuilder pb=new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "PspTema1.x");
        pb.directory(directorio);
        System.out.printf("Directorio de trabajo: %s%n", pb.directory());
        Process p=pb.start();

        try{
            InputStream er=p.getErrorStream();
            int c;
            while((c=er.read()) != -1){
                System.out.print((char) c);
            }
            er.close();
        }catch(Exception e){
            System.out.println(e);;
        }
    }
}

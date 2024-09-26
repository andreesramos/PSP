import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo3 {
    public static void main(String[] args) throws IOException {
        File directorio = new File("");//Falta el directorio
        ProcessBuilder pb=new ProcessBuilder("Ejemplo2");
        pb.directory(directorio);
        System.out.printf("Directoriode trabajo: %s%n", pb.directory());
        Process p=pb.start();

        try{
            InputStream is=p.getInputStream();
            int c;
            while((c=is.read()) != -1){
                System.out.print((char) c);
            }
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

import java.io.File;
import java.io.IOException;

public class EjecutarLeerNombre{
    public static void main(String[] args)  throws IOException{

        File directorio = new File("/");
        ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre");
        pb.directory(directorio);

        System.out.println();
    }
}
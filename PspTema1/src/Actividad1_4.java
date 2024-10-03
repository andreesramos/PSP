import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actividad1_4 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb=new ProcessBuilder("bash", "-c", "ls");

        File fOut=new File("salida.txt");
        File fErr=new File("error.txt");
        File fIn=new File("entrada.txt");

        pb.redirectInput(fIn);
        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        pb.start();

        InputStreamReader in=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(in);
        String texto;

        try{
            System.out.println("Introduce una cadena...");
            texto= br.readLine();
            System.out.println("Cadena escrita: " + texto);
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

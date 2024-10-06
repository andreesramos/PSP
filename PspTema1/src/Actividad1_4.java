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

    }
}

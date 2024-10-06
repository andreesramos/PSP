import java.io.File;

public class Actividad1_5 {
    public static void main(String[] args) {
        ProcessBuilder pb=new ProcessBuilder("bash", "-c", "ls");
        File fIn=new File("entrada2.txt");
        File fOut=new File("salida2.txt");

        pb.redirectInput(ProcessBuilder.Redirect.from(fIn));
        pb.redirectOutput(ProcessBuilder.Redirect.to(fOut));

        
    }
}

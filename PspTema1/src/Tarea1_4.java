import java.io.File;
import java.io.IOException;

//Crea un proceso en el que la salida y la salida de error del Ejemplo5 se guardan en un archivo de texto. Además
//la entrada se redirije desde otro fichero.

public class Tarea1_4 {
    public static void main(String[] args) throws IOException {
        File directorio=new File("/home/usuario/PSP/PspTema1/out/production/PspTema1");
        ProcessBuilder pb=new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "Ejemplo5");
        pb.directory(directorio);

        File fOut=new File("salida.txt");
        File fErr=new File("error.txt");
        File fIn=new File("entrada.txt");

        pb.redirectInput(fIn);
        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        pb.start();


    }
}

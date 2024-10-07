import java.io.*;

//Muestra la salida del Ejemplo5 por consola y posteriormente redirije la entrada y la salida a un fichero de texto

public class Tarea1_5 {
    public static void main(String[] args) throws IOException {
        File directorio=new File("/home/usuario/PSP/PspTema1/out/production/PspTema1");
        ProcessBuilder pb=new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "Ejemplo5");
        pb.directory(directorio);

        Process p=pb.start();

        OutputStream os=p.getOutputStream();
        os.write("Andres Ramos\n".getBytes());
        os.flush();

        try{
            InputStream is=p.getInputStream();
            int c;
            while((c = is.read()) != -1){
                System.out.print((char) c);
            }
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        File fIn=new File("entrada2.txt");
        File fOut=new File("salida2.txt");

        pb.redirectInput(ProcessBuilder.Redirect.from(fIn));
        pb.redirectOutput(ProcessBuilder.Redirect.to(fOut));
        pb.start();
        
    }
}

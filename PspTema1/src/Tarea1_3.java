import java.io.*;

/*Ejecuta LeerNumeros.java*/

public class Tarea1_3 {
    public static void main(String[] args) throws IOException {

        File directorio= new File("/home/usuario/PSP/PspTema1/out/production/PspTema1");
        ProcessBuilder pb=new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "LeerNumeros");
        pb.directory(directorio);

        Process p=pb.start();

        OutputStream os=p.getOutputStream();

        os.write("10\n".getBytes());
        os.write("16\n".getBytes());
        os.flush();

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

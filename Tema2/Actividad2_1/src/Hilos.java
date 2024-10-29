//Crea un hilo que imprime infinitamente la palabra pasada como parámetro

public class Hilos extends Thread{
    String palabra;

    //Se le pasa una palabra como parámetro
    public Hilos(String palabra){
        this.palabra = palabra;
    }

    public void run(){
        //Bloque para capturar la excepción
        try{
            //Bucle infinito para imprimir la palabra
            while(true){
                System.out.println(palabra);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

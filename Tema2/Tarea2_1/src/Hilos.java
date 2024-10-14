//Crea un hilo que imprime infinitamente la palabra pasada como parámetro

public class Hilos extends Thread{
    String palabra;

    public Hilos(String palabra){
        this.palabra = palabra;
    }

    public void run(){
        try{
            while(true){
                System.out.println(palabra);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

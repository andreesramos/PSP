import java.applet.Applet;

public class Actividad2_7 extends Thread implements Runnable{

    static class Hilo extends Thread{
        int cont;
        //Constructor del hilo
        public Hilo(String nombre){
            setName(nombre);
        }
        public void run(){
            //Mientras que el contador no llegue a 5000 se aumentara en 1
            if(cont<5000){
                cont++;
            }else{
                throw new RuntimeException("El contador ha alcanzado el limite de 5000");
            }
            System.out.println(getName() + " contador vale " + cont);
        }
    }

    public static void main(String[] args) {
        //Creacion de los hilos
        Hilo h1=new Hilo("Hilo1");
        Hilo h2=new Hilo("Hilo2");
        Hilo h3=new Hilo("Hilo3");
        Hilo h4=new Hilo("Hilo4");
        Hilo h5=new Hilo("Hilo5");

        //Bucle que ejecuta el metodo run de cada hilo para incrementar el contador
        while(true){
            h1.run();
            h2.run();
            h3.run();
            h4.run();
            h5.run();
        }
    }
}

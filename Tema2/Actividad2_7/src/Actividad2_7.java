import java.applet.Applet;

public class Actividad2_7 extends Thread implements Runnable{


    static class Hilo extends Thread{
        static int cont;
        public Hilo(String nombre){
            setName(nombre);
        }
        public void run(){
            if(cont<50){
                cont++;
            }else{
                //Lanzar exepcion
            }
            System.out.println(getName() + " contador vale " + cont);
        }
    }


    public static void main(String[] args) {
        Hilo h1=new Hilo("Hilo1");
        Hilo h2=new Hilo("Hilo2");
        Hilo h3=new Hilo("Hilo3");
        Hilo h4=new Hilo("Hilo4");
        Hilo h5=new Hilo("Hilo5");

        while(true){
            h1.run();
            h2.run();
            h3.run();
            h4.run();
            h5.run();
        }
    }
}

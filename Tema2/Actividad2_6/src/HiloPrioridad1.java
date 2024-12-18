public class HiloPrioridad1 extends Thread {
    private int c=0;
    private boolean stopHilo=false;

    //Constructor
    public HiloPrioridad1(String nombre) {
        super(nombre);
    }

    //Devuelve el contador
    public int getContador(){
        return c;
    }

    //Para el hilo
    public void pararHilo(){
        stopHilo=true;
    }

    //Mientras que el hilo no este parado, incrementa el contador
    public void run(){
        while(!stopHilo){
            try{
                Thread.sleep(2);
            }catch(Exception e){}
            c++;
        }
        System.out.println("Fin hilo " + this.getName());
    }
}

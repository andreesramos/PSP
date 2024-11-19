public class Main extends Thread{

    public void run(){

    }

    public static void main(String[] args) {
        Saldo saldo=new Saldo(0);
        Thread hilo1=new Thread(saldo);
        Thread hilo2=new Thread(saldo);
        Thread hilo3=new Thread(saldo);

        
    }
}
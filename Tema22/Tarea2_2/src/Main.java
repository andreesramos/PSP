public class Main {
    public static void main(String[] args) {

        Hilos h1=new Hilos(1);
        Hilos h2=new Hilos(2);
        Hilos h3=new Hilos(3);
        Hilos h4=new Hilos(4);
        Hilos h5=new Hilos(5);

        new Thread(h1).start();
        new Thread(h2).start();
        new Thread(h3).start();
        new Thread(h4).start();
        new Thread(h5).start();
    }
}
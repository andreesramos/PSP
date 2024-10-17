public class HilosEjercicio1 extends Thread {
    int num;

    public HilosEjercicio1(int num) {
        this.num = num;
    }

    public void run(){
        System.out.println("Hola mundo " + num);
    }
}

public class HilosEjercicio1 extends Thread {
    int num;

    //Recogemos el número pasado como parámetro
    public HilosEjercicio1(int num) {
        this.num = num;
    }

    //Imprimimos "Hola mundo" con el número
    public void run(){
        System.out.println("Hola mundo " + num);
    }
}

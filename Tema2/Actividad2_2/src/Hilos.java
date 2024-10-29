//Imprime "Hola mundo" junto a un numero
public class Hilos implements Runnable {
    int id;

    public Hilos(int num) {
        this.id = num;
    }

    public void run(){
        System.out.println("Hola mundo " + id);
    }
}

//Crea 5 hilos con números diferentes para imprimirlos por pantalla junto con "Hola mundo"
public class Ejercicio1 {
    public static void main(String[] args) {

        //Creación de los hilos
        HilosEjercicio1 h1=new HilosEjercicio1(1);
        HilosEjercicio1 h2=new HilosEjercicio1(2);
        HilosEjercicio1 h3=new HilosEjercicio1(3);
        HilosEjercicio1 h4=new HilosEjercicio1(4);
        HilosEjercicio1 h5=new HilosEjercicio1(5);

        //Ejecución de los hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
    }
}
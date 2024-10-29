//Inicializa los 5 hilos y los ejecuta.
//Al usar el metodo sleep se ordenan los hilos debido al uso de tiempos largos de espera, que
//reducen la competencia entre estos.

public class Ejercicio2 {
    public static void main(String[] args) {

        //Creación de los hilos
        HilosEjercicio2 h1=new HilosEjercicio2("hola", 1);
        HilosEjercicio2 h2=new HilosEjercicio2("buenas", 2);
        HilosEjercicio2 h3=new HilosEjercicio2("tardes", 3);
        HilosEjercicio2 h4=new HilosEjercicio2("como", 4);
        HilosEjercicio2 h5=new HilosEjercicio2("estas", 5);

        //Ejecución de los hilos
        new Thread(h1).start();
        new Thread(h2).start();
        new Thread(h3).start();
        new Thread(h4).start();
        new Thread(h5).start();
    }
}
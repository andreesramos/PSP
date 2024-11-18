/*En este programa los 5 hilos comparten comparten el mismo contador, por
lo que cada vez que se ejecuta un hilo el contador aumentara en 1 para todos
 */
public class Actividad2_7_sincronizada extends Thread implements Runnable{
    //Clase con el contador compartido
    static class Contador {
        private static int cont = 0;

        //Metodo sincronizado para incrementar el contador
        public static synchronized void incrementar(String nombreHilo) {
            if (cont < 5000) {
                cont++;
                System.out.println(nombreHilo + " contador vale " + cont);
            } else {
                throw new RuntimeException("El contador ha alcanzado el límite de 5000");
            }

        }
    }

    //Clase Hilo que usa el el metodo sincronizado
    static class Hilo extends Thread {
        //Constructor de los hilos
        public Hilo(String nombre) {
            setName(nombre);
        }

        //Llama al metodo sincronizado
        public void run() {
            Contador.incrementar(getName());
        }
    }

    public static void main(String[] args) {
        //Instanciar el contador
        Contador contador = new Contador();

        //Creacion de los hilos
        Hilo h1 = new Hilo("Hilo1");
        Hilo h2 = new Hilo("Hilo2");
        Hilo h3 = new Hilo("Hilo3");
        Hilo h4 = new Hilo("Hilo4");
        Hilo h5 = new Hilo("Hilo5");

        //Ejecucion de los hilos
        while(true){
            h1.run();
            h2.run();
            h3.run();
            h4.run();
            h5.run();
        }
    }
}


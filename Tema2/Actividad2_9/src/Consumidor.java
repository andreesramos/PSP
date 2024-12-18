public class Consumidor extends Thread {
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    @Override
    public void run() {
        int valor=0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // Obtiene un número de la cola.
            // Verifica si hay un valor disponible (-1 indica que la cola está vacía).
            System.out.println(i + " => Consumidor: " + n + ", consume: " + valor);


            // Añadimos sleep() para ralentizar al consumidor.
            try {
                sleep(150); // Simula un retraso en el consumo.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class Productor extends Thread {
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); // Coloca un número en la cola.
            System.out.println(i + " => Productor: " + n + ", produce: " + i);

            // Se puede quitar este sleep() para que el productor sea más rápido.
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


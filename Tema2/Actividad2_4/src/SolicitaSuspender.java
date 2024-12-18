public class SolicitaSuspender {
    private boolean suspender;

    // Metodo para suspender el hilo
    public synchronized void set(boolean b) {
        suspender = b;
        notifyAll();
    }

    // Metodo para verificar si el hilo esta suspendido
    public synchronized boolean estaSuspendido() {
        return suspender;
    }

    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender) {
            wait(); // Pausa el hilo
        }
    }
}



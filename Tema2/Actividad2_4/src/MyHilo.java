public class MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private int cont = 0; // Inicialización del contador
    private boolean enEjecucion = true; // Booleano para controlar la ejecución del hilo

    // Metodo para suspender el hilo
    public void Suspende() {
        suspender.set(true);
    }

    // Metodo para reanudar el hilo
    public void Reanuda() {
        suspender.set(false);
    }

    // Obtener el valor actual del contador
    public int getContador() {
        return cont;
    }

    // Incrementar el contador solo si no está suspendido y lo muestra
    public boolean incrementarContador() {
        if (!suspender.estaSuspendido() && enEjecucion) { // Verifica si no está suspendido
            cont++;
            System.out.println("Contador: " + cont);
            return true;
        }
        return false;
    }

    // Metodo para finalizar el hilo
    public void finalizar() {
        enEjecucion = false;
    }

    public void run() {
        while (enEjecucion) {
            try {
                suspender.esperandoParaReanudar(); // Pausa el hilo si está suspendido
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interrumpido.");
            }
        }
        System.out.println("Hilo finalizado.");
    }
}

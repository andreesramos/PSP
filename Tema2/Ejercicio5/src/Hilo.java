public class Hilo extends Thread {
    private Ejercicio5 frame;
    private int x = 1; //Posicion inicial en x
    private int direccion = 1; //1: derecha, -1: izquierda
    private boolean moviendo = true; //Controla si el hilo esta corriendo

    public Hilo(Ejercicio5 frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            if (moviendo) {
                x += direccion; //Mueve la letra en la direccion actual

                //Rebote en los limites
                if (x >= frame.getLimite()) {
                    direccion = -1; //Cambiar direccion a izquierda
                }
                if (x <= 1) {
                    direccion = 1; //Cambiar direccion a derecha
                }

                frame.setX(x); //Actualizar posicion de la letra
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Cambia el estado para pausar o reanudar el hilo
    public void cambioEstado() {
        moviendo = !moviendo;
    }

    public boolean isMoviendo() {
        return moviendo;
    }
}

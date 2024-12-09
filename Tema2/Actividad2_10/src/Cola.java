public class Cola {
    private String mensaje;
    private boolean disponible = false;

    //Metodo sincronizado para obtener un valor de la cola
    public synchronized String get() {
        while (!disponible) { //Espera si la cola está vacia
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false; //Marca la cola como vacia
        notify(); //Notifica al productor que puede agregar valores
        return mensaje;
    }

    //Metodo sincronizado para colocar un valor en la cola
    public synchronized void put(String valor) {
        while (disponible) { //Espera si la cola esta llena
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mensaje = valor; //Coloca el mensaje en la cola
        disponible = true; //Marca la cola como llena
        notify(); //Notifica al consumidor que puede recoger el valor
    }
}


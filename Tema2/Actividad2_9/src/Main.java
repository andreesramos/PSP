/*
No se obtiene la salida desada porque hay ciertas inconsistencias:
- El productor sobrescribe valores antes de que el consumidor los lea.
- El consumidor intenta consumir cuando la cola está vacía.
 */
public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola(); // Cola compartida.

        // Crea y lanza un Productor y un Consumidor.
        Productor productor = new Productor(cola, 1);
        Consumidor consumidor = new Consumidor(cola, 1);

        productor.start();
        consumidor.start();
    }
}

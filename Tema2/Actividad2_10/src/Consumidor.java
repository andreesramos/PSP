public class Consumidor extends Thread {
    private Cola cola;
    private int id;

    public Consumidor(Cola c, int id) {
        cola = c;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            String mensaje = cola.get(); //Obtener mensaje de la cola
            System.out.print(mensaje + " ");
        }
    }
}


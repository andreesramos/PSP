public class Productor extends Thread {
    private Cola cola;

    public Productor(Cola c) {
        cola = c;
    }

    @Override
    public void run() {
        String[] mensajes = {"PING", "PONG"}; //Mensajes
        int i=0;

        while (true) {
            String mensaje = mensajes[i % 2]; //Alternar entre PING y PONG
            cola.put(mensaje); //Colocar en la cola
            i++; //Incrementar el indice
        }
    }
}


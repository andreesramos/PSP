public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola(); //Instancia de Cola

        //Crear productor y concumidores
        Productor productor = new Productor(cola);
        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);

        //Iniciar productor y consumidores
        productor.start();
        consumidor1.start();
        consumidor2.start();
    }
}

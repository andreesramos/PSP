/* Varía el orden de ejecución de los hilos, pero no depende de la prioridad
* en este caso porque hay pocos hilos*/
public class EjemploHiloPrioridad2 extends Thread {
    //Constructor
    EjemploHiloPrioridad2(String nom) {
        this.setName(nom);
    }

    public void run() {
        System.out.println("Ejecutando [" + getName() + "]");
        for (int i=1; i<=5; i++){
            System.out.println("\t(" + getName() + ": " + i + ")");
        }

    }

    public static void main(String[] args) {
        //Crear hilos
        EjemploHiloPrioridad2 h1 = new EjemploHiloPrioridad2("Uno");
        EjemploHiloPrioridad2 h2 = new EjemploHiloPrioridad2("Dos");
        EjemploHiloPrioridad2 h3 = new EjemploHiloPrioridad2("Tres");
        EjemploHiloPrioridad2 h4 = new EjemploHiloPrioridad2("Cuatro");
        EjemploHiloPrioridad2 h5 = new EjemploHiloPrioridad2("Cinco");

        //Asignar prioridades
        h1.setPriority(Thread.MAX_PRIORITY);
        h2.setPriority(7);
        h3.setPriority(Thread.NORM_PRIORITY);
        h4.setPriority(3);
        h5.setPriority(Thread.MIN_PRIORITY);

        //Ejecutar hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
    }
}

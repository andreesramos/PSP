/* No siempre el valor del contador depende de la prioridad que le hayamos asignado
* cuando contamos con pocos hilos ejecutándose*/
public class EjemploHiloPrioridad1 {
    public static void main(String[] args) {
        //Creacion de hilos
        HiloPrioridad1 h1 = new HiloPrioridad1("Hilo1");
        HiloPrioridad1 h2 = new HiloPrioridad1("Hilo2");
        HiloPrioridad1 h3 = new HiloPrioridad1("Hilo3");

        //Asignacion de prioridad
        h1.setPriority(Thread.MIN_PRIORITY);
        h2.setPriority(Thread.NORM_PRIORITY);
        h3.setPriority(Thread.MAX_PRIORITY);

        //Ejecucion de los hilos
        h1.start();
        h2.start();
        h3.start();

        try{
            Thread.sleep(10000);
        }catch(Exception e){}

        //Paramos los hilos
        h1.pararHilo();
        h2.pararHilo();
        h3.pararHilo();

        //Imprimimos los resultados
        System.out.println("h1 (Prioridad Minima): " + h1.getContador());
        System.out.println("h2 (Prioridad Normal): " + h2.getContador());
        System.out.println("h3 (Prioridad Maxima): " + h3.getContador());
    }
}

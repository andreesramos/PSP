//Inicializa los hilos con "TIC" y "TAC". Hay veces que se muestran de forma ordenada
// y veces que no, porque la ejecución de los hilos es concurrente, es decir, se ejecutan
// de forma simultánea así que no hay ninguna seguridad de que se impriman en orden.

public class Main {
    public static void main(String[] args) {

        //Creo dos hilos y les paso las palabras TIC y TAC
        Hilos h1 = new Hilos("TIC");
        Hilos h2 = new Hilos("TAC");

        //Ejecuto los hilos
        h1.start();
        h2.start();
    }
}
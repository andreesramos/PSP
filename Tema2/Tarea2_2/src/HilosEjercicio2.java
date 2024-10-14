//Muestra el mensaje junto a una cadena y un identificador pasados como parametros.
//Tiene un tiempo de espera para mostrarlo dependiendo del id.

public class HilosEjercicio2 implements Runnable {
    int id;
    String cadena;

    public HilosEjercicio2(String cadena, int num) {
        this.id = num;
        this.cadena = cadena;
    }

    public void run(){
        try{
            Thread.sleep((long) id *1000);
            System.out.println("Hola mundo " + cadena + " " + id);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

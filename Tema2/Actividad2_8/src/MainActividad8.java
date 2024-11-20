public class MainActividad8 extends Thread{

    public static void main(String[] args) {
        //Creacion objeto Saldo con saldo inicial
        Saldo saldo=new Saldo(1000);
        System.out.println("Saldo inicial: " + saldo.getSaldo());

        //Creacion de hilos compartiendo el mismo saldo
        HiloSaldo hilo1 = new HiloSaldo(saldo, "Hilo1", 200);
        HiloSaldo hilo2 = new HiloSaldo(saldo, "Hilo2", 300);
        HiloSaldo hilo3 = new HiloSaldo(saldo, "Hilo3", 150);

        //Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("Saldo final: " + saldo.getSaldo());
    }
}
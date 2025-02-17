package Ejercicio1;

public class Cuenta extends Thread{
    private int saldo;
    private int saldoMax;

    // Constructor
    public Cuenta(int saldo, int saldoMax) {
        this.saldo = saldo;
        this.saldoMax = saldoMax;
    }

    // Metodo para obtener el saldo
    public synchronized int getSaldo() {
        return this.saldo;
    }

    // Metodo para incrementar saldo
    public synchronized void ingreso(int incremento) {
        try{
            Thread.sleep((int)(Math.random()*4000)); //Sleep
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        if(this.saldo + incremento > this.saldoMax) {
            System.out.println("Incremento de: " + incremento + ".Saldo maximo superado");
        }else{
            this.saldo += incremento;
            System.out.println("Incremento de: " + incremento);
        }
    }

    // Metodo para restar saldo
    public synchronized void reintegro(int decremento) {
        try{
            Thread.sleep((int)(Math.random()*4000)); //Sleep
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        if(this.saldo - decremento < 0) {
            System.out.println("Retirada de: " + decremento + ".Saldo negativo");
        }else{
            this.saldo -= decremento;
            System.out.println("Retirada de: " + decremento);
        }
    }
}

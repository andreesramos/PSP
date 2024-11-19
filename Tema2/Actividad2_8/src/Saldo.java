public class Saldo extends Thread{
    private int saldo;

    //Constructor
    public Saldo(int saldo) {
        this.saldo = saldo;
    }

    //Metodo para obtener el saldo con sleep aleatorio
    public synchronized int getSaldo() {
        try{
            Thread.sleep((int)(Math.random()*4000));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return saldo;
    }

    //Metodo para establecer el nuevo saldo con sleep aleatorio
    public synchronized void setSaldo(int saldo) {
        try{
            Thread.sleep((int)(Math.random()*4000));
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        this.saldo = saldo;
    }

    /*Aumenta el saldo con la cantidad pasada como parametro e imprime la cantidad de saldo antes y
    despues de modificarla
     */
    public /*synchronized*/ void sumarSaldo(String nom, int can){
        int saldoInicial = this.saldo;
        this.saldo += can;

        System.out.println("Hilo " + nom + " añade " + can + " al saldo. Saldo inicial: " + saldoInicial + ", Saldo final: " + this.saldo);
    }
}

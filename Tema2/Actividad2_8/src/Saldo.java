public class Saldo extends Thread{
    private double saldo;

    public Saldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        try{
            sleep((long)(Math.random()*1000));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void sumar(String nom, double can){
        System.out.println(nom + " va a ingresar " + can + ", el saldo actual es de: " + getSaldo());

        try{
            sleep(500);
        }catch(InterruptedException e){}

        setSaldo(this.getSaldo() + can);
        System.out.println("El saldo tras el ingreso es de: " + getSaldo());
    }
}

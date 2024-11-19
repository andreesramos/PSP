public class Saldo extends Thread{
    private double saldo;

    //Constructor
    public Saldo(double saldo) {
        this.saldo = saldo;
    }

    //Metodo para obtener el saldo
    public double getSaldo() {
        try{
            sleep((long)(Math.random()*2000));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return saldo;
    }

    //Metodo para darle valor al saldo
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /*Aumenta el saldo con la cantidad pasada como parametro e imprime la cantidad de saldo antes y
    despues de modificarla
     */
    public synchronized void sumar(String nom, double can){
        System.out.println(nom + " va a ingresar " + can + ", el saldo actual es de: " + getSaldo());

        try{
            sleep(500);
        }catch(InterruptedException e){}

        setSaldo(this.getSaldo() + can);
        System.out.println("El saldo tras el ingreso es de: " + getSaldo());
    }
}

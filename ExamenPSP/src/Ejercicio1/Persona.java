package Ejercicio1;

public class Persona extends Thread{
    private String nombre;
    private Cuenta cuenta;
    private boolean parar=false;

    // Constructor
    public Persona(String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    // Metodo para parar el hilo
    public void parar(){
        parar=true;
    }

    // Metodo run del hil para ingresar y reintegrar dinero
    @Override
    public void run(){
        while (!parar){
            cuenta.ingreso(((int) (Math.random()*500+1)));
            cuenta.reintegro(((int) (Math.random()*500+1)));
            cuenta.ingreso(((int) (Math.random()*500+1)));
            cuenta.reintegro(((int) (Math.random()*500+1)));
            parar();
        }
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}

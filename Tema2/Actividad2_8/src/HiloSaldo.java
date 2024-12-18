public class HiloSaldo extends Thread {
    private Saldo saldoCompartido;
    private int cantidad;

    //Constructor
    public HiloSaldo(Saldo saldo, String nombre, int cantidad) {
        super(nombre);
        this.saldoCompartido = saldo;
        this.cantidad = cantidad;
    }

    //Uso del metodo sincronizado para añadir saldo
    @Override
    public void run() {
        saldoCompartido.sumarSaldo(this.getName(), cantidad);
    }
}

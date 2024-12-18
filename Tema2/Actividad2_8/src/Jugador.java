//Representa a un jugador como hilo
public class Jugador extends Thread{
    private final int idJugador;
    private final Arbitro arbitro; //Objeto arbitro compartido

    //Constructor
    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        //Bucle hasta que termine el juego
        while (!arbitro.isJuegoAcabado()) {
            synchronized (arbitro) {
                //Verifica si es el turno del jugador y si el juego no ha terminado
                if (arbitro.getTurno() == idJugador && !arbitro.isJuegoAcabado()) {
                    //Numero aleatorio entre 1 y 10
                    int numJugado = (int)(Math.random()*10+1);
                    //Arbitro comprueba la jugada
                    arbitro.comprobarJugada(idJugador, numJugado);
                }
            }

            //Pequeña pausa
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

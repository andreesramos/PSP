// Controla el flujo del juego, mantiene el turno y verifica las jugadas.
public class Arbitro {
    private final int numAdivinar; //Numero a adivinar(aleatorio)
    private int turno;
    private boolean juegoAcabado;
    private int numJugadores;

    public Arbitro(int numJugadores) {
        //Genera el numero a adivinar(entre 1 y 10)
        this.numAdivinar = 1 + (int)(10*Math.random());
        this.turno = 1; //Primer turno
        this.juegoAcabado = false;
        this.numJugadores = numJugadores;
        System.out.println("NUMERO A ADIVINAR: " + numAdivinar);
    }

    //Metodo para obtener el turno
    public synchronized int getTurno() {
        return turno;
    }

    //Metodo para verificar si el juego ha acabado
    public synchronized boolean isJuegoAcabado() {
        return juegoAcabado;
    }

    //Metodo para comprobar la jugada de un jugador
    public synchronized void comprobarJugada(int idJugador, int numJugado){
        if(!juegoAcabado) {
            //Imprime jugada
            System.out.println("Jugador " + idJugador + " dice: " + numJugado);

            if(numJugado == numAdivinar) {
                //Si acierta se termina el juego
                System.out.println("Jugador " + idJugador + " gana, adivino el numero!!");
                juegoAcabado = true;
            }else{
                //Si no acierta pasa el turno al siguiente
                turno = (turno % numJugadores) + 1;
                System.out.println(" Le toca a Jug" + turno);
            }
        }
    }
}

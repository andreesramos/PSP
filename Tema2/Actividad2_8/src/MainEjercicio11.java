//Inicia el juego
public class MainEjercicio11 {
    public static void main(String[] args) {
        int numJugadores = 3; //En este caso tres jugadores
        Arbitro arbitro = new Arbitro(numJugadores);//Objeto arbitro compartido

        //Crear y lanzar hilos
        for(int i=1; i<=numJugadores; i++) {
            Jugador jugador = new Jugador(i, arbitro);
            jugador.start();
        }
    }
}

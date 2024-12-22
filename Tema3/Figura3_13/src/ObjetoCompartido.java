public class ObjetoCompartido {
    private int numero;
    private boolean acabo;
    private int ganador;

    public ObjetoCompartido(int numero) {
        this.numero = numero; //Numero a adivinar
        this.acabo = false;
    }

    public boolean seAcabo() {return acabo;}
    public int getGanador() {return ganador;}

    public synchronized String nuevaJugada(int jugador, int suNumero){
        String cad="";
        if(!seAcabo()){
            if(!seAcabo()){
                if(suNumero > numero){ //demasiado grande
                    cad = "Numero demasiado grande";
                }
                if(suNumero < numero){ //demasiado pequeño
                    cad = "Numero demasiado bajo";
                }
                if(suNumero == numero){
                    cad = "Jugador " + jugador + " gana, adivino el numero: " + numero;
                    acabo = true;
                    ganador = jugador;
                }
            }
        }else{
            cad = "Jugador " + ganador + " adivino el numero: " + numero;
        }

        return cad;
    } //nuevaJugada
}

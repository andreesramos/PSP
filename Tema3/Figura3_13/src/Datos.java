import java.io.Serializable;

public class Datos implements Serializable {
    String cadena; //Cadena que se intercambia con el sevidor
    int intentos; //intentos que lleva el jugador, hasta 5
    int identificador; //id del jugador
    boolean gana; //true si el jugador adivina el numero
    boolean juega; //true si el jugador juega, false juego finalizado

    public Datos(String cadena, int intentos, int identificador) {
        this.cadena = cadena;
        this.intentos = intentos;
        this.identificador = identificador;
        this.gana = false;
        this.juega = true;
    }

    public Datos(){
        super();
    }

    public String getCadena() {return cadena;}
    public int getIntentos() {return intentos;}
    public int getIdentificador() {return identificador;}
    public boolean isGana() {return gana;}
    public boolean isJuega() {return juega;}

    public void setCadena(String cadena) {this.cadena = cadena;}
    public void setIntentos(int intentos) {this.intentos = intentos;}
    public void setIdentificador(int identificador) {this.identificador = identificador;}
    public void setGana(boolean gana) {this.gana = gana;}
    public void setJuega(boolean juega) {this.juega = juega;}
}

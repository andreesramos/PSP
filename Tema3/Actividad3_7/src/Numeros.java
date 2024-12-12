import java.io.Serializable;

public class Numeros implements Serializable {
    int numero;
    long cuadrado;
    long cubo;

    //Constructor
    Numeros(int num, long cuadrado, long cubo){
        this.numero = num;
        this.cuadrado=cuadrado;
        this.cubo=cubo;
    }

    Numeros(){}

    //Metodos get y set
    public int getNumero(){return numero;}
    public long getCuadrado(){return cuadrado;}
    public long getCubo(){return cubo;}

    public void setNumero(int num){this.numero=num;}
    public void setCuadrado(long cuadrado){this.cuadrado=cuadrado;}
    public void setCubo(long cubo){this.cubo=cubo;}

}
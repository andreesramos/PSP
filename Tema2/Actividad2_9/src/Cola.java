public class Cola{
    private int numero;
    private boolean disponible = false; //Cola vacia

    public int get(){
        if(disponible){ //Si hay numero en la cola
            disponible = false; //Se pone cola a vacia
            return numero; // Se devuelve el numero
        }
        return -1;
    }

    public void put(int valor){
        numero = valor; //Valor en la cola
        disponible = true; // Cola llena
    }
}

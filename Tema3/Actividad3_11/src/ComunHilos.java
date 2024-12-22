import java.net.Socket;

public class ComunHilos {
    int CONEXIONES; //Nº DE CONEXIONES TOTALES
    int ACTUALES; //NUMERO DE CONEXIONES ACTUALES
    int MAXIMO; //NUMERO DE CONEXIONES PERMITIDAS
    Socket tabla[] = new Socket[MAXIMO];
    String mensajes; //MENSAJES DEL CHAT


    public ComunHilos(int maximo, int actuales, int conexiones, Socket tabla[]) {
        MAXIMO = maximo;
        ACTUALES = actuales;
        CONEXIONES = conexiones;
        this.tabla = tabla;
        mensajes = "";
    }

    public ComunHilos(){super();}

    public int getMAXIMO(){return MAXIMO;}
    public void setMAXIMO(int maximo){MAXIMO = maximo;}

    public int getCONEXIONES(){return CONEXIONES;}
    public void setCONEXIONES(int conexiones){CONEXIONES = conexiones;}

    public String getMensajes(){return mensajes;}
    public synchronized void setMensajes(String mensajes){this.mensajes = mensajes;}

    public int getACTUALES(){return ACTUALES;}
    public synchronized void setACTUALES(int actuales){ACTUALES = actuales;}

    //añadir sockets al array de sockets
    public synchronized void addTabla(Socket s, int i){
        tabla[i] = s;
    }
    public Socket getElementoTabla(int i){return tabla[i];}
}//ComunHilos

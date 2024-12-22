import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread {
    DataInputStream fentrada;
    Socket socket=null;
    ComunHilos comun;

    public HiloServidorChat(Socket s, ComunHilos comun){
        this.socket=s;
        this.comun=comun;
        try{
            //CREO FLUJO DE ENTRADA PARA LEER LOS MENSAJES
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comun.getACTUALES());

        //NADA MAS CONECTARSE LE ENVIO TODOS LOS MENSAJES
        String texto = comun.getMensajes();
        EnviarMensajesaTodos(texto);

        while(true){
            String cadena = "";
            try{
                cadena= fentrada.readUTF();
                if(cadena.trim().equals("*")){ //CLIENTE DESCONECTA
                    comun.setACTUALES(comun.getACTUALES()-1);
                    System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comun.getACTUALES());
                    break; //Sale del bucle
                }
                comun.setMensajes(comun.getMensajes()+cadena + "\n");
                EnviarMensajesaTodos(comun.getMensajes());
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }//fin while

        //se cierra el socket del cliente
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }//run

    //ENVIA LOS EMSNAJES DEL CHAT A LOS CLIENTES
    private void EnviarMensajesaTodos(String texto){
        int i;
        //reorremos tabla de sockets para enviarles los mensajes
        for(i=0; i<comun.getCONEXIONES(); i++){
            Socket s1 = comun.getElementoTabla(i);
            if(!s1.isClosed()){
                try{
                    DataOutputStream fsalida2 = new DataOutputStream(s1.getOutputStream());
                    fsalida2.writeUTF(texto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//for
    }//EnviarMensajesaTodos
}//HiloServidorChat
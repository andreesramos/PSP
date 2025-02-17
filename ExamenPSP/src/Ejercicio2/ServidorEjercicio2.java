package Ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/* Este es el servidor, en el tengo que crear un array de 5 profesores. Se ejecutara un bucle
* para ir aceptando clientes y asignadoles un id. Recibira un id de algun profesor y le mandara al cliente el
* profesor correspondiente hasta recibir un *. La idea es que pueda aceptar varios clientes. No funciona,
* al cliente no le llega la asignacion del id. No lo he acabado a tiempo*/

public class ServidorEjercicio2 {
    public static void main(String[] args) {
        int puerto = 6000;
        int cont=1;//Contador para los clientes
        Profesor[] profesores= new Profesor[5];
        Asignatura[] asignaturas1=new Asignatura[3];
        Asignatura[] asignaturas2=new Asignatura[3];

        //Creación de asignaturas
        Asignatura asignatura1 = new Asignatura(1, "DIU");
        Asignatura asignatura2 = new Asignatura(2, "Acceso a datos");
        Asignatura asignatura3 = new Asignatura(3, "PSP");
        Asignatura asignatura4 = new Asignatura(4, "HLC");
        Asignatura asignatura5 = new Asignatura(5, "Moviles");
        Asignatura asignatura6 = new Asignatura(6, "Empresas");
        asignaturas1[0]=asignatura1;
        asignaturas1[1]=asignatura2;
        asignaturas1[2]=asignatura3;
        asignaturas2[0]=asignatura4;
        asignaturas2[1]=asignatura5;
        asignaturas2[2]=asignatura6;

        Especialidad especialidad1=new Especialidad(1, "Especialidad 1");
        Especialidad especialidad2=new Especialidad(2, "Especialidad 2");
        Especialidad especialidad3=new Especialidad(3, "Especialidad 3");
        Especialidad especialidad4=new Especialidad(4, "Especialidad 4");
        Especialidad especialidad5=new Especialidad(5, "Especialidad 5");

        Profesor profesor1 = new Profesor(1, "Antonio", asignaturas1, especialidad1);
        Profesor profesor2 = new Profesor(2, "Paco", asignaturas2, especialidad2);
        Profesor profesor3 = new Profesor(3, "Francisco", asignaturas2, especialidad3);
        Profesor profesor4 = new Profesor(4, "Luis", asignaturas1, especialidad4);
        Profesor profesor5 = new Profesor(5, "Juan", asignaturas1, especialidad5);
        profesores[0]=profesor1;
        profesores[1]=profesor2;
        profesores[2]=profesor3;
        profesores[3]=profesor4;
        profesores[4]=profesor5;


        //Pedimos el numero de clientes por teclado
        System.out.println("Introduce el numero de clientes que el servidor puede atender");
        int num=new Scanner(System.in).nextInt();

        try{
            //Creacion del serverSocket
            ServerSocket servidor = new ServerSocket(puerto);

            while(cont<=num){
                System.out.println("Esperando al cliente...");
                Socket cliente = servidor.accept();//Aceptamos cliente

                //Mandamos al cliente el mensaje con su numero de cliente
                OutputStream salida = cliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(salida);
                ObjectOutputStream objetoSalida = new ObjectOutputStream(flujoSalida);
                DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

                flujoSalida.writeUTF("Cliente numero " + cont);//Mandamos mensaje
                cont++;//Aumentamos el contador

                String idRecibido = flujoEntrada.readUTF();
                System.out.println("Id solicitado: " + idRecibido);

                //Recorremos los profesores comparando con el id recibido
                for(int i=1; i<=5; i++){
                    if(idRecibido.equals(String.valueOf(profesores[i].idprofesor))){
                        objetoSalida.writeObject(profesores[i]);
                    }else{
                        objetoSalida.writeUTF("Profesor no encontrado");
                    }
                }

                flujoEntrada.close();
                flujoSalida.close();
                objetoSalida.close();
                salida.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

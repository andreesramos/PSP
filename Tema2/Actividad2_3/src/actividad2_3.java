//Crea dos hilos, los inicializa con un numero y empieza a contar hasta que
//se pulse el botón correspondiente
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad2_3 extends Applet implements ActionListener {
    private Button b1, b2;
    private Font fuente;
    private HiloContador h1;
    private HiloContador h2;

    class HiloContador extends Thread {
        long cont1;
        long cont2;

        //Constructor con ambos contadores como parámetros
        public HiloContador(long cont1, long cont2) {
            this.cont1 = cont1;
            this.cont2 = cont2;
        }

        //Devuelve el valor del contador 1
        public long getCont1() {
            return cont1;
        }
        //Devuelve el valor del contador 2
        public long getCont2() {return cont2;}

        public void run(){
            //Obtiene el hilo que se está ejecutando actualmente
            Thread hiloActual = Thread.currentThread();
            
            //Bucle para la lógica del primer hilo. Se mantiene mientras
            // el hilo actual sea igual a h1
            while(h1 == hiloActual){
                //Bloque para capturar la excepción
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //Actualiza la interfaz
                repaint();
                //Suma uno al contador
                cont1++;
            }

            //Bucle para la lógica del segundo hilo. Se mantiene mientras
            // el hilo actual sea igual a h2
            while(h2 == hiloActual){
                //Bloque para capturar la excepción
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //Actualiza la interfaz
                repaint();
                //Suma uno al contador
                cont2++;
            }
        }

    }

    //Controla los botones
    public void actionPerformed(ActionEvent e) {
        //Si se alguno de los botones, se detiene el hilo correspondiente
        //y cambia el texto del botón
        if(e.getSource() == b1){
            h1.stop();
            b1.setLabel("Finalizado Hilo 1");
        }else if(e.getSource() == b2){
            h2.stop();
            b2.setLabel("Finalizado Hilo 2");
        }
    }

    public void start(){
    }

    //Prepara la pantalla
    public void init(){
        //Inicializa ambos hilos y los ejecuta
        h1 = new HiloContador(0,0);
        h1.start();
        h2 = new HiloContador(0,0);
        h2.start();
        
        //Color de fondo amarillo
        setBackground(Color.yellow);
        
        //Creación de los botones, se les añade texto y funcionalidad
        add(b1=new Button("Finalizar Hilo 1"));
        b1.addActionListener(actividad2_3.this::actionPerformed);
        add(b2=new Button("Finalizar Hilo 2"));
        b2.addActionListener(actividad2_3.this::actionPerformed);
        
        //Personalización de fuente
        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    //
    public void paint(Graphics g){
        long c1=h1.getCont1();
        long c2=h2.getCont2();
        //Limpia el área
        g.clearRect(0, 0, 400, 400);
        //Establece la fuente
        g.setFont(fuente);
        //Dibuja los valores de los contadores de los hilos
        g.drawString("Hilo 1: " + (Long.toString((long)c1)), 80, 100);
        g.drawString("Hilo 2: " + (Long.toString((long)c2)), 80, 150);
    }

    //Función para parar los hilos
    public void stop(){
        h1=null;
        h2=null;
    }
}

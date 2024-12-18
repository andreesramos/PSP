import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{
    HiloContador hilo1, hilo2;
    private JButton b1, b2; // Botones del JFrame

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
            while(hilo1 == hiloActual){
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
            while(hilo2 == hiloActual){
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

        public void detenerHilo(){
            hilo1=null;
            hilo2=null;
        }
    }

    public Main() {
        // Configuración de la ventana principal
        setTitle("Contador de Hilos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Cambiar el color de fondo del contenido del JFrame
        getContentPane().setBackground(Color.YELLOW);

        // Añadir botones y configurar su acción
        b1 = new JButton("Finalizar Hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Finalizar Hilo 2");
        b2.addActionListener(this);
        add(b2);

        // Inicializar los hilos con contadores iniciales
        hilo1 = new HiloContador(8, 20);
        hilo2 = new HiloContador(8, 20);

        // Iniciar los hilos desde el principio
        hilo1.start();
        hilo2.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Llama a la clase base para limpiar la ventana
        g.setFont(new Font("Verdana", Font.BOLD, 20)); // Configurar fuente
        g.drawString("Hilo 1: " + hilo1.getCont1(), 80, 100); // Mostrar el contador1
        g.drawString("Hilo 2: " + hilo2.getCont2(), 80, 150); // Mostrar el contador2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Controlar Hilo 1
            hilo1.detenerHilo(); // Detener hilo 1
            b1.setText("Finalizado Hilo 1"); // Cambiar texto del botón
        }

        if (e.getSource() == b2) { // Controlar Hilo 2
            hilo2.detenerHilo(); // Detener hilo 2
            b2.setText("Finalizado Hilo 2"); // Cambiar texto del botón
        }
        repaint(); // Vuelve a dibujar para reflejar los cambios
    }

    public static void main(String[] args) {
        Main app = new Main(); // Crea una instancia de la aplicación
        app.setVisible(true); // Muestra la ventana
    }
}
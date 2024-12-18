import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad2_5 extends JFrame implements ActionListener {
    private JButton b1, b2;
    private Font fuente;
    private HiloContador h1;
    private HiloContador h2;

    public actividad2_5() {
        // Configuración de la ventana
        setTitle("Actividad con JFrame y Hilos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.yellow);
        setLayout(new BorderLayout());

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.yellow);

        // Configuración de botones
        b1 = new JButton("Finalizar Hilo 1");
        b1.addActionListener(this);
        panelBotones.add(b1);

        b2 = new JButton("Finalizar Hilo 2");
        b2.addActionListener(this);
        panelBotones.add(b2);

        // Añade el panel de botones a la ventana
        add(panelBotones, BorderLayout.SOUTH);

        // Personalización de fuente
        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializa los hilos
        h1 = new HiloContador(10, 100);
        h1.start();
        h2 = new HiloContador(10, 100);
        h2.start();
    }

    // Clase interna para los hilos contadores
    class HiloContador extends Thread {
        long cont1;
        long cont2;
        boolean parar = false;

        //Constructor con ambos contadores como parámetros
        public HiloContador(long cont1, long cont2) {
            this.cont1 = cont1;
            this.cont2 = cont2;
        }

        //Detiene el hilo
        public void interrumpir() {
            parar = true;
        }

        //Devuelve el valor del contador 1
        public long getCont1() {
            return cont1;
        }

        //Devuelve el valor del contador 2
        public long getCont2() {
            return cont2;
        }

        public void run() {
            //Obtiene el hilo que se está ejecutando actualmente
            Thread hiloActual = Thread.currentThread();

            //Bucle para la lógica del primer hilo. Se mantiene mientras
            // el hilo actual sea igual a h1 y parar sea falso
            while (h1 == hiloActual && !parar) {
                //Bloque para capturar la excepción
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cont1++; //Suma uno al contador
                repaint(); //Actualiza la interfaz
            }

            //Bucle para la lógica del segundo hilo. Se mantiene mientras
            // el hilo actual sea igual a h2 y parar sea falso
            while (h2 == hiloActual && !parar) {
                //Bloque para capturar la excepción
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cont2++; //Suma uno al contador
                repaint(); //Actualiza la interfaz
            }
        }
    }

    //Controla los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        //Si se pulsa alguno de los botones, se detiene el hilo correspondiente
        //y cambia el texto del botón
        if (e.getSource() == b1) {
            h1.interrumpir();
            b1.setText("Finalizado Hilo 1");
        } else if (e.getSource() == b2) {
            h2.interrumpir();
            b2.setText("Finalizado Hilo 2");
        }
    }

    // Dibujamos en el JFrame
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        long c1 = h1.getCont1();
        long c2 = h2.getCont2();

        // Limpia y redibuja el área con el estado actualizado de los contadores
        g.clearRect(50, 50, 300, 200);
        g.setFont(fuente); //Establece la fuente
        //Dibuja los valores de los contadores de los hilos
        g.drawString("Hilo 1: " + Long.toString(c1), 80, 100);
        g.drawString("Hilo 2: " + Long.toString(c2), 80, 150);
    }

    public static void main(String[] args) {
        // Inicia la aplicación
        SwingUtilities.invokeLater(() -> {
            actividad2_5 frame = new actividad2_5();
            frame.setVisible(true);
        });
    }
}

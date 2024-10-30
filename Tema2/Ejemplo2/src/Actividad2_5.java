import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_5 extends JFrame implements ActionListener {
    private JButton b1, b2;
    private Font fuente;
    private HiloContador h1;
    private HiloContador h2;

    public Actividad2_5() {
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

        public HiloContador(long cont1, long cont2) {
            this.cont1 = cont1;
            this.cont2 = cont2;
        }

        public void interrumpir() {
            parar = true;
        }

        public long getCont1() {
            return cont1;
        }

        public long getCont2() {
            return cont2;
        }

        public void run() {
            Thread hiloActual = Thread.currentThread();
            while (h1 == hiloActual && !parar) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cont1++;
                repaint();
            }

            while (h2 == hiloActual && !parar) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cont2++;
                repaint();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            h1.interrumpir();
            b1.setText("Finalizado Hilo 1");
        } else if (e.getSource() == b2) {
            h2.interrumpir();
            b2.setText("Finalizado Hilo 2");
        }
    }

    // Sobrescribimos paintComponent para dibujar en el JFrame
    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Llama a la implementación de la superclase para limpiar el fondo
        long c1 = h1.getCont1();
        long c2 = h2.getCont2();

        // Limpia y redibuja el área con el estado actualizado de los contadores
        g.clearRect(50, 50, 300, 200);
        g.setFont(fuente);
        g.drawString("Hilo 1: " + Long.toString(c1), 80, 100);
        g.drawString("Hilo 2: " + Long.toString(c2), 80, 150);
    }

    public static void main(String[] args) {
        // Inicia la aplicación
        SwingUtilities.invokeLater(() -> {
            Actividad2_5 frame = new Actividad2_5();
            frame.setVisible(true);
        });
    }
}

/*
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad2_5 extends Applet implements ActionListener {
    private Button b1, b2;
    private Font fuente;
    private HiloContador h1;
    private HiloContador h2;

    class HiloContador extends Thread {
        long cont1;
        long cont2;
        boolean parar = false;

        public HiloContador(long cont1, long cont2) {
            this.cont1 = cont1;
            this.cont2 = cont2;
        }

        public void interrumpir(){
            parar = true;
        }

        public long getCont1() {
            return cont1;
        }
        public long getCont2() {return cont2;}

        public void run(){
            Thread hiloActual = Thread.currentThread();
            while(h1 == hiloActual && !parar){
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                repaint();
                cont1++;
            }

            while(h2 == hiloActual && !parar){
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                repaint();
                cont2++;
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            h1.interrumpir();
            b1.setLabel("Finalizado Hilo 1");
        }else if(e.getSource() == b2){
            h2.interrumpir();
            b2.setLabel("Finalizado Hilo 2");
        }
    }

    public void start(){
    }

    public void init(){
        h1 = new HiloContador(10,100);
        h1.start();
        h2 = new HiloContador(10,100);
        h2.start();
        setBackground(Color.yellow);
        add(b1=new Button("Finalizar Hilo 1"));
        b1.addActionListener(actividad2_5.this::actionPerformed);
        fuente = new Font("Verdana", Font.BOLD, 26);
        add(b2=new Button("Finalizar Hilo 2"));
        b2.addActionListener(actividad2_5.this::actionPerformed);
    }

    public void paint(Graphics g){
        long c1=h1.getCont1();
        long c2=h2.getCont2();
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("Hilo 1: " + (Long.toString((long)c1)), 80, 100);
        g.drawString("Hilo 2: " + (Long.toString((long)c2)), 80, 150);
    }


}
 */

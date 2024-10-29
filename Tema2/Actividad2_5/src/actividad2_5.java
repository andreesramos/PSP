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

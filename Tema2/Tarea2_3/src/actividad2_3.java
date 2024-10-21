import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actividad2_3 extends Applet implements ActionListener {

    private  boolean parar1, parar2;
    private Button b1, b2;

    class HiloContador extends Thread {
        private Thread h1;
        private Thread h2;
        long cont1, cont2;
        private Font fuente;

        public HiloContador(int cont){
            this.cont1 = cont;
            this.cont2 = cont;
        }

        public long getCont() {
            return cont1;
        }

        public void init(){
            add(b1=new Button("Finalizar Hilo 1"));
            b1.addActionListener(actividad2_3.this::actionPerformed);
            fuente = new Font("Verdana", Font.BOLD, 26);
            add(b2=new Button("Finalizar Hilo 2"));
            b2.addActionListener(actividad2_3.this::actionPerformed);
        }

        public void run(){
            parar1 = false;
            parar2 = false;
            Thread hiloActual = Thread.currentThread();
            while(h1 == hiloActual && !parar1){
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                b1.repaint();
                cont1++;
            }

            while(h2 == hiloActual && !parar2){
                try{
                    Thread.sleep(300);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                b2.repaint();
                cont2++;
            }
        }

        public void paint(Graphics g){
            g.clearRect(0, 0, 400, 400);
            g.setFont(fuente);
            if(Thread.currentThread() == h1){
                g.drawString(Long.toString((long)cont1), 80, 100);
            }else if(Thread.currentThread() == h2){
                g.drawString(Long.toString((long)cont2), 80, 100);
            }

        }

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            parar1 = true;
        }else if(e.getSource() == b2){
            parar2 = true;
        }
    }


}
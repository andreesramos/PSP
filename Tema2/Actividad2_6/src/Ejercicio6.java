import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio6 extends Applet /*implements ActionListener*/ {
    Letras hilo = null;
    Letras hilo2 = null;
    Letras hilo3 = null;

    int x, y, j = 2;
    int x2, y2, j2 = 2;
    int x3, y3, j3 = 2;

    private Font fuente;
    String gana = " ";

    class Letras extends Thread {
        private boolean stopHilo = false;
        int x, y, j;

        void PararHilo(boolean p) {
            this.stopHilo = p;
        }

        Letras(int x, int y, int j) {
            this.x = x;
            this.y = y;
            this.j = j;
        }

        public void run() {
            while (!stopHilo) {
                this.x = this.x + this.j;
                repaint();
                try {
                    sleep((long) (Math.random() * 1000 + 1));
                } catch (InterruptedException e) {
                }
            }
            System.out.println("Fin hilo " + getName());
        }// run

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        void setX(int x) {
            this.x = x;
        }

        void setY(int y) {
            this.y = y;
        }

        void setJ(int j) {
            this.j = j;
        }

    }// fin Letras

    public void init() {
        x = 1;
        y = 50;

        x2 = 1;
        y2 = 100;

        x3 = 1;
        y3 = 150;

        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    public void start() {

        if (hilo == null) {
            hilo = new Letras(x, y, j);
            hilo.setPriority(Thread.MIN_PRIORITY);
            hilo.start();
        }

        if (hilo2 == null) {
            hilo2 = new Letras(x2, y2, j2);
            hilo2.setPriority(Thread.MAX_PRIORITY);
            hilo2.start();
        }
        if (hilo3 == null) {
            hilo3 = new Letras(x3, y3, j3);
            hilo3.setPriority(Thread.NORM_PRIORITY);
            hilo3.start();
        }
    }

    public void paint(Graphics g) {
        g.setFont(fuente);// fuente
        g.drawString("Ganador: " + gana, 2, 200);
        g.drawRect(getSize().width - 15, 1, getSize().width - 15, getSize().height - 2);

        if (hilo.getX() >= getSize().width - 15) {
            gana = "1";
            PararHilos();
        }
        g.drawString("1", hilo.getX(), hilo.getY());

        if (hilo2.getX() >= getSize().width - 15) {
            gana = "2";
            PararHilos();
        }
        g.drawString("2", hilo2.getX(), hilo2.getY());

        if (hilo3.getX() >= getSize().width - 15) {
            gana = "3";
            PararHilos();
        }
        g.drawString("3", hilo3.getX(), hilo3.getY());
        g.drawString("Ganador: " + gana, 2, 200);

    }

    private void PararHilos() {
        hilo.PararHilo(true);
        hilo2.PararHilo(true);
        hilo3.PararHilo(true);
    }

    /*private Button boton;
    private JSlider slider1, slider2, slider3;
    private JProgressBar progressBar1, progressBar2, progressBar3;
    private JTextField text1, text2, text3;
    private Label label;
    HiloContador h1, h2, h3;

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == boton){
            h1.start();
            h2.start();
            h3.start();
        }
    }

    class HiloContador extends Thread{
        long cont;
        //boolean parar=false;

        public HiloContador(){
            this.cont=0;
        }

        public void interrumpir(){
            //parar=true;
            interrupt();
        }

        public long getCont(){
            return this.cont;
        }

        public void run(){
            while(!isInterrupted()){
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                cont++;
                repaint();
            }
        }
    }*/
}

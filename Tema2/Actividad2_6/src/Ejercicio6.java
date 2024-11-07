import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio6 extends Applet implements ActionListener {
    private Button boton;
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
    }
}

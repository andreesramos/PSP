import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HiloContador extends Thread, Applet implements ActionListener {
    private Thread h1;
    private Thread h2;
    long cont1;
    long cont2;
    private Button b1, b2;
    private Font fuente;
    private  boolean parar1;
    private  boolean parar2;

    public HiloContador(int cont){
        this.cont1 = cont;
        this.cont2 = cont;
    }

    public long getCont() {
        return cont;
    }

    public void start(){}

    public void init(){
        add(b1=new Button("Finalizar Hilo 1"));
        b1.addActionListener(this);
        add(b2=new Button("Finalizar Hilo 2"));
        b2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    public void run(){
        parar = false;
        Thread hiloActual = Thread.currentThread();
        while(h == hiloActual && !parar){
            try{
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
            cont++;
        }
    }

    public void paint(Graphics g){
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString(Long.toString((long)cont), 80, 100);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            parar1 = true;
        }else if(e.getSource() == b2){
            parar2 = true;
        }
    }
}

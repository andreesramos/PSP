import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio5 extends JFrame {
    private int x = 1; // Posición inicial en x
    private int y = 100; // Posición fija en y
    private JButton boton;
    private Hilo hilo;

    public Ejercicio5() {
        setTitle("Letra en movimiento");
        setSize(400, 200); //Tamaño inicial
        setLayout(null);

        boton = new JButton("Finalizar Hilo");
        boton.setBounds(140, 120, 120, 30);
        add(boton);

        //Evento del boton para pausar o reanudar el hilo
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hilo != null) {
                    hilo.cambioEstado(); //Cambiar estado del hilo
                    String texto;
                    if(hilo.isMoviendo()) {
                        texto="Finalizar Hilo";
                    }else{
                        texto="Reanudar Hilo";
                    }
                    boton.setText(texto);
                }
            }
        });

        //Crea el hilo, lo incia y comienza a mover la letra
        hilo = new Hilo(this);
        hilo.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("A", x, y); //Dibuja la letra en la posicion (x, y)
    }

    //Metodopara actualizar la posicion de la letra
    public void setX(int x) {
        this.x = x;
        repaint();
    }

    //Metodo para obtener el limite derecho
    public int getLimite() {
        return getWidth() - 50;
    }

    public static void main(String[] args) {
        Ejercicio5 frame = new Ejercicio5();
        frame.setVisible(true);
    }
}

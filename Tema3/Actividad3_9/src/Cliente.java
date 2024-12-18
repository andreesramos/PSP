import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends JFrame {
    private JTextField txtInput;
    private JTextArea txtOutput;
    private JLabel label;
    private JButton btnEnviar, btnLimpiar, btnSalir;
    private PrintWriter fsalida;
    private BufferedReader fentrada;
    private Socket cliente;

    public Cliente() {
        //Configuracion de la ventana
        setTitle("VENTANA DEL CLIENTE - EJERCICIO 5");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Escribe texto: ");
        label.setBounds(50, 30, 220, 30);
        add(label);

        txtInput = new JTextField();
        txtInput.setBounds(50, 80, 220, 30);
        add(txtInput);

        txtOutput = new JTextArea();
        txtOutput.setBounds(50, 130, 220, 30);
        txtOutput.setEditable(false);
        add(txtOutput);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(320, 30, 100, 30);
        add(btnEnviar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(320, 130, 100, 30);
        add(btnLimpiar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(320, 80, 100, 30);
        add(btnSalir);

        conectarServidor();

        //Acción para el botón "Enviar"
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        //Acción para el botón "Limpiar"
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtInput.setText("");
                txtOutput.setText("");
            }
        });

        //Acción para el botón "Salir"
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje("*");
                desconectarServidor();
                System.exit(0);
            }
        });
    }

    private void conectarServidor() {
        try {
            //Conexion con el servidor
            cliente = new Socket("localhost", 44444);
            fsalida = new PrintWriter(cliente.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage());
            System.exit(0);
        }
    }

    private void enviarMensaje() {
        String mensaje = txtInput.getText();
        if (!mensaje.isEmpty()) {
            fsalida.println(mensaje); //Encia mensaje al servidor
            try {
                String respuesta = fentrada.readLine();
                txtOutput.append(respuesta + "\n");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al recibir respuesta: " + e.getMessage());
            }
        }
    }

    private void enviarMensaje(String mensaje) {
        fsalida.println(mensaje); //Envía mensaje directo
    }

    private void desconectarServidor() { //Desconexión
        try {
            fsalida.close();
            fentrada.close();
            cliente.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Cliente ventana = new Cliente();
        ventana.setVisible(true);
    }
}

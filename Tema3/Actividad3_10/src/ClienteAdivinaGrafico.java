import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteAdivinaGrafico extends JFrame {
    //Declaramos los componentes de la interfaz gráfica
    private JTextField txtNumero;
    private JTextField txtMensajes;
    private JLabel lblIntentos, lblIdJugador;
    private JTextField txtIdJugador, txtIntentos;
    private JButton btnEnviar, btnSalir;

    //Declaramos los componentes para la comunicación con el servidor
    private ObjectOutputStream fsalida;
    private ObjectInputStream fentrada;
    private Socket cliente;

    //Declaramos variables para almacenar datos
    private int idJugador;
    private int intentos;

    public ClienteAdivinaGrafico() {
        // Configuración de la ventana
        setTitle("JUGADOR - ADIVINA UN NÚMERO");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblIdJugador = new JLabel("ID JUGADOR: ");
        lblIdJugador.setBounds(50, 20, 200, 20);
        add(lblIdJugador);

        txtIdJugador = new JTextField();
        txtIdJugador.setBounds(150, 20, 50, 20);
        txtIdJugador.setEditable(false);
        add(txtIdJugador);

        lblIntentos = new JLabel("INTENTOS: ");
        lblIntentos.setBounds(250, 20, 200, 20);
        add(lblIntentos);

        txtIntentos = new JTextField();
        txtIntentos.setBounds(350, 20, 50, 20);
        txtIntentos.setEditable(false);
        add(txtIntentos);

        JLabel lblNumero = new JLabel("NUMERO A ADIVINAR :");
        lblNumero.setBounds(50, 80, 200, 20);
        add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(200, 80, 50, 30);
        add(txtNumero);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(280, 80, 100, 30);
        add(btnEnviar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 200, 100, 30);
        add(btnSalir);

        txtMensajes = new JTextField();
        txtMensajes.setBounds(50, 160, 390, 30);
        txtMensajes.setEditable(false);

        add(txtMensajes);

        //Conectamos con el servidor y obtenemos los datos iniciales
        conectarServidor();

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarNumero(); //Enviamos el número ingresado al servidor
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarConexion(); //Cerramos la conexión con el servidor
                System.exit(0); //Salimos de la aplicación
            }
        });
    }

    //Metodo para conectar con el servidor y obtener los datos iniciales
    private void conectarServidor() {
        try {
            cliente = new Socket("localhost", 6001);
            fsalida = new ObjectOutputStream(cliente.getOutputStream());
            fentrada = new ObjectInputStream(cliente.getInputStream());

            // Obtener datos iniciales del servidor
            Datos datos = (Datos) fentrada.readObject();
            idJugador = datos.getIdentificador();
            intentos = datos.getIntentos();
            txtIdJugador.setText("" + idJugador);
            txtIntentos.setText("" + intentos);
            txtMensajes.setText(datos.getCadena());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage());
            System.exit(0);
        }
    }

    //Metodo para enviar el número ingresado al servidor
    private void enviarNumero() {
        try {
            //Obtenemos el número ingresado por el usuario
            String numero = txtNumero.getText();
            if (numero.isEmpty()) {
                txtMensajes.setText("Por favor, introduce un número válido.");
                return;
            }

            //Creamos un objeto Datos para enviar el número al servidor
            Datos datos = new Datos();
            datos.setCadena(numero);
            fsalida.reset();
            fsalida.writeObject(datos);

            //Recibimos la respuesta del servidor
            Datos respuesta = (Datos) fentrada.readObject();
            intentos = respuesta.getIntentos();
            txtIntentos.setText("" + intentos);
            txtMensajes.setText(respuesta.getCadena());

            if (intentos > 5) { //Si supera los 5 intentos se acaba
                JOptionPane.showMessageDialog(this, "Has superado el límite de 5 intentos. El juego terminará.", "Límite de intentos alcanzado", JOptionPane.WARNING_MESSAGE);
                cerrarConexion();
                System.exit(0);
            }

            if (!respuesta.isJuega()) {
                if (respuesta.isGana()) {
                    JOptionPane.showMessageDialog(this, "¡HAS GANADO!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Juego finalizado. Otro jugador ha ganado.", "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
                }
                cerrarConexion();
                System.exit(0);
            }
        } catch (Exception e) {
            txtMensajes.setText("Error al enviar número: " + e.getMessage());
        }
    }

    //Metodo para cerrar la conexión con el servidor
    private void cerrarConexion() {
        try {
            if (fsalida != null) fsalida.close();
            if (fentrada != null) fentrada.close();
            if (cliente != null) cliente.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClienteAdivinaGrafico cliente = new ClienteAdivinaGrafico();
        cliente.setVisible(true);
    }
}
package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ejercicio3 extends JFrame {
    private JTextField servidor, usuario, clave, puerto;
    private JButton btnConectar, btnMensajes;
    private JTextArea salida;
    private POP3SClient pop3;

    public Ejercicio3() {
        // Configuración de la ventana principal
        setTitle("Conexión POP3");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Creacion de los componentes de la interfaz
        servidor = new JTextField("pop.gmail.com");
        usuario = new JTextField("andresrn16@gmail.com");
        clave = new JPasswordField("hyfn oymk ffre sdmu");
        puerto = new JTextField("995");
        btnConectar = new JButton("Conectar");
        btnMensajes = new JButton("Recuperar Mensajes");
        salida = new JTextArea();
        salida.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(salida); // Permitir desplazamiento

        // Configuracion del diseño de la ventana
        setLayout(new GridLayout(7, 1));
        add(new JLabel("Servidor:"));
        add(servidor);
        add(new JLabel("Usuario:"));
        add(usuario);
        add(new JLabel("Contraseña:"));
        add(clave);
        add(new JLabel("Puerto:"));
        add(puerto);
        add(btnConectar);
        add(btnMensajes);
        add(scrollPane);

        // Desactivar el boton de recuperar mensajes
        btnMensajes.setEnabled(false);

        btnConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        btnMensajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveMessages();
            }
        });
    }

    //Metodo para conectar al servidor POP3 utilizando los datos ingresados
    private void connectToServer() {
        String server = servidor.getText();
        String username = usuario.getText();
        String password = clave.getText();
        int puerto = Integer.parseInt(this.puerto.getText());

        pop3 = new POP3SClient(true); // Crear cliente POP3 con SSL
        try {
            pop3.connect(server, puerto); // Conectar al servidor
            salida.append("Conexión realizada con el servidor\n");

            // Intentar iniciar sesion
            if (!pop3.login(username, password)) {
                salida.append("Error al hacer login\n");
            } else {
                salida.append("Login exitoso\n");
                btnMensajes.setEnabled(true); // Habilitar el boton para recuperar mensajes
            }

        } catch (IOException e) {
            salida.append("Error de conexión: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    //Metodo para recuperar la lista de mensajes disponibles en el servidor
    private void retrieveMessages() {
        try {
            POP3MessageInfo[] men = pop3.listMessages(); // Obtener lista de mensajes

            if (men == null) {
                salida.append("No se puede listar los mensajes\n");
            } else {
                salida.append("Número de mensajes: " + men.length + "\n");
                recuperarMensajes(men); // Llamar al metodo para obtener detalles de cada mensaje
            }

            // Cerrar sesion y desconectar del servidor
            pop3.logout();
            pop3.disconnect();
        } catch (IOException e) {
            salida.append("Error al recuperar los mensajes: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    //Metodo para mostrar la informacion de los mensajes recuperados
    private void recuperarMensajes(POP3MessageInfo[] mensajes) throws IOException {
        for (int i = 0; i < mensajes.length; i++) {
            salida.append("\nMensaje " + (i + 1) + "\n");
            POP3MessageInfo msgInfo = mensajes[i];

            // Imprimir detalles del mensaje
            salida.append("ID: " + msgInfo.identifier + "\n");
            salida.append("Número: " + msgInfo.number + "\n");
            salida.append("Tamaño: " + msgInfo.size + " bytes\n");

            // Obtener el identificador unico del mensaje
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
            if (pmi != null) {
                salida.append("Identificador único: " + pmi.identifier + "\n");
            } else {
                salida.append("No se pudo obtener el identificador único.\n");
            }
        }
    }

    //Metodo principal que inicia la interfaz grafica de la aplicacion
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejercicio3().setVisible(true);
            }
        });
    }
}

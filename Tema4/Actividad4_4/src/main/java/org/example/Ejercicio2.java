package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.net.smtp.*;

public class Ejercicio2 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("CLIENTE SMTP BÁSICO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2));

        JTextField smtpServerField = new JTextField();
        JTextField portField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextArea messageArea = new JTextArea(10, 40);

        JRadioButton noTlsButton = new JRadioButton("Sin TLS");
        JRadioButton withTlsButton = new JRadioButton("Con TLS", true);
        ButtonGroup tlsGroup = new ButtonGroup();
        tlsGroup.add(noTlsButton);
        tlsGroup.add(withTlsButton);

        JButton sendButton = new JButton("Enviar mensaje");

        panel.add(new JLabel("Servidor SMTP:"));
        panel.add(smtpServerField);
        panel.add(new JLabel("Puerto:"));
        panel.add(portField);
        panel.add(new JLabel("Usuario:"));
        panel.add(usernameField);
        panel.add(new JLabel("Clave:"));
        panel.add(passwordField);
        panel.add(new JLabel("Remitente:"));
        panel.add(fromField);
        panel.add(new JLabel("Destinatario:"));
        panel.add(toField);
        panel.add(new JLabel("Asunto:"));
        panel.add(subjectField);
        panel.add(new JLabel("Redacta el cuerpo del mensaje:"));
        panel.add(new JScrollPane(messageArea));
        panel.add(noTlsButton);
        panel.add(withTlsButton);
        panel.add(sendButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String server = smtpServerField.getText();
                int port = Integer.parseInt(portField.getText());
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String from = fromField.getText();
                String to = toField.getText();
                String subject = subjectField.getText();
                String message = messageArea.getText();
                boolean useTls = withTlsButton.isSelected();

                if (message.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El mensaje no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    sendEmail(server, port, username, password, from, to, subject, message, useTls);
                    JOptionPane.showMessageDialog(frame, "MENSAJE ENVIADO CON ÉXITO.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error al enviar el mensaje: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static void sendEmail(String server, int port, String username, String password, String from, String to, String subject, String message, boolean useTls) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        try {
            client.connect(server, port);
            if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                throw new IOException("Conexión rechazada por el servidor.");
            }

            client.ehlo(server);

            if (useTls && !client.execTLS()) {
                throw new IOException("Fallo en la negociación TLS.");
            }

            if (!client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                throw new IOException("Autenticación fallida.");
            }

            SimpleSMTPHeader header = new SimpleSMTPHeader(from, to, subject);
            client.setSender(from);
            client.addRecipient(to);

            Writer writer = client.sendMessageData();
            if (writer == null) {
                throw new IOException("Error al enviar los datos del mensaje.");
            }
            writer.write(header.toString());
            writer.write(message);
            writer.close();

            if (!client.completePendingCommand()) {
                throw new IOException("Fallo al completar la transacción.");
            }
        } finally {
            client.disconnect();
        }
    }
}

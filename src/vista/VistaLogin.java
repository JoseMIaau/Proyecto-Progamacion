package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaLogin extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    public VistaLogin() {

        setLayout(new GridBagLayout());
        setBackground(EstilosUI.FONDO);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosUI.FONDO);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("SuperCuricó");
        titulo.setFont(EstilosUI.FONT_TITLE);
        titulo.setForeground(EstilosUI.VERDE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        panel.add(titulo, gbc);

        JLabel subtitulo = new JLabel("Iniciar sesión");
        subtitulo.setFont(EstilosUI.FONT_BOLD);

        gbc.gridy = 1;
        panel.add(subtitulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(EstilosUI.FONT_NORMAL);

        gbc.gridy = 2;
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(EstilosUI.FONT_NORMAL);

        gbc.gridy = 3;
        panel.add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(EstilosUI.FONT_NORMAL);

        gbc.gridy = 4;
        panel.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(EstilosUI.FONT_NORMAL);

        gbc.gridy = 5;
        panel.add(txtContrasena, gbc);

        btnIngresar = EstilosUI.roundedButton(
                "Ingresar",
                EstilosUI.VERDE,
                Color.WHITE
        );

        gbc.gridy = 6;
        gbc.insets = new Insets(20, 8, 8, 8);
        panel.add(btnIngresar, gbc);

        add(panel);
    }

    
}
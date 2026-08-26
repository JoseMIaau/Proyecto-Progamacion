package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BaseFrame extends JFrame {

    // Colores y las fuentes del baseframe 
    protected static final Color VERDE = new Color(46, 103, 57);
    protected static final Color VERDE_CLARO = new Color(166, 211, 160);
    protected static final Color FONDO = Color.WHITE;

    protected static final Font FONT_NORMAL =
            new Font("SansSerif", Font.PLAIN, 15);

    protected static final Font FONT_BOLD =
            new Font("SansSerif", Font.BOLD, 16);

    protected static final Font FONT_TITLE =
            new Font("SansSerif", Font.BOLD, 28);

    // CardLayout para cambiar entre vistas
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel root = new JPanel(cardLayout);

    public BaseFrame() {
        super("SuperCuricó");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 720));
        setSize(1180, 780);
        setLocationRelativeTo(null);

        root.add(new VistaMenuPrincipal(this), "INICIO");
        root.add(new VistaProductos(this), "PRODUCTOS");
        root.add(new VistaCarrito(this), "CARRO");

        setContentPane(root);

        cardLayout.show(root, "INICIO");
    }

    public void mostrarVista(String vista) {
        cardLayout.show(root, vista);
    }

    // Botón redondeado reutilizable
    protected JButton roundedButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(getBackground());
                g2.fillRoundRect(
                        0, 0,
                        getWidth(),
                        getHeight(),
                        30, 30
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(fg);
        button.setBackground(bg);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(new EmptyBorder(7, 18, 7, 18));
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // Botón para iconos
    protected JButton iconButton(String text) {
        JButton b = new JButton(text);

        b.setFont(new Font("SansSerif", Font.PLAIN, 26));
        b.setForeground(Color.WHITE);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        return b;
    }

    // Punto de entrada
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            new BaseFrame().setVisible(true);
        });
    }
}
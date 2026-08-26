package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VistaCarrito extends JPanel {

    private final BaseFrame frame;

    public VistaCarrito(BaseFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(BaseFrame.FONDO);

        add(createCartHeader(), BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2));
        content.setBackground(BaseFrame.FONDO);

        JPanel left = new JPanel();
        left.setBackground(BaseFrame.FONDO);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(30, 55, 30, 45));


        //PRODUCTOS AGREGADOS MANUALMENTE
        //TRABAJO PARA BACKEND: EN ESTA PARTE DEBERIA IR LA CONEXION LOGICA 
        left.add(createCartRow("Plátano", "$1.490", new Color(246, 220, 67)));
        left.add(Box.createVerticalStrut(20));
        left.add(createCartRow("Lechuga", "$790", new Color(115, 181, 77)));
        left.add(Box.createVerticalStrut(20));
        left.add(createCartRow("Brócoli", "$990", new Color(65, 133, 69)));

        JPanel right = new JPanel();
        right.setBackground(BaseFrame.FONDO);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(new CompoundBorder(
                new MatteBorder(0, 2, 0, 0, Color.BLACK),
                new EmptyBorder(35, 45, 35, 45)
        ));

        right.add(summaryLine("Plátano", "$1.490"));
        right.add(Box.createVerticalStrut(12));
        right.add(summaryLine("Lechuga", "$790"));
        right.add(Box.createVerticalStrut(12));
        right.add(summaryLine("Brócoli", "$990"));
        right.add(Box.createVerticalStrut(25));

        JLabel total = new JLabel("Total:     $3270");
        total.setFont(new Font("SansSerif", Font.BOLD, 22));
        total.setAlignmentX(Component.CENTER_ALIGNMENT);
        right.add(total);

        right.add(Box.createVerticalStrut(30));

        JButton pagar = frame.roundedButton(
                "PAGAR",
                BaseFrame.VERDE_CLARO,
                BaseFrame.VERDE
        );

        pagar.setAlignmentX(Component.CENTER_ALIGNMENT);
        pagar.setMaximumSize(new Dimension(160, 44));
        pagar.addActionListener(e -> showCheckoutDialog());

        right.add(pagar);

        content.add(left);
        content.add(right);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createCartHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BaseFrame.VERDE);
        header.setBorder(new EmptyBorder(10, 28, 10, 28));

        JButton back = frame.iconButton("☰");
        back.addActionListener(e -> frame.mostrarVista("PRODUCTOS"));
        header.add(back, BorderLayout.WEST);

        JLabel title = new JLabel("Mi carrito", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setForeground(BaseFrame.VERDE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(6, 28, 6, 28));
        header.add(title, BorderLayout.CENTER);

        JButton cart = frame.iconButton("🛒");
        cart.setFont(new Font("SansSerif", Font.PLAIN, 28));
        header.add(cart, BorderLayout.EAST);

        return header;
    }

    private JPanel createCartRow(String name, String price, Color productColor) {
        JPanel row = new JPanel(new BorderLayout(18, 0));
        row.setBackground(BaseFrame.FONDO);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 115));

        JPanel product = new JPanel();
        product.setPreferredSize(new Dimension(120, 90));
        product.setBackground(productColor);
        product.setBorder(new LineBorder(new Color(225, 225, 225), 1, true));

        row.add(product, BorderLayout.WEST);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 28));
        controls.setOpaque(false);

        JComboBox<String> qty = new JComboBox<>(
                new String[]{"0,5", "1", "1,5", "2"}
        );

        qty.setPreferredSize(new Dimension(95, 32));
        controls.add(qty);

        //BOTON DE BORRAR 
        //PARA BACKEND: AQUI ENLAZAR CON BORRAR DEL INVENTARIO REAL
        
        JButton trash = new JButton("🗑");
        trash.setFont(new Font("SansSerif", Font.PLAIN, 22));
        trash.setForeground(BaseFrame.VERDE);
        trash.setBorderPainted(false);
        trash.setContentAreaFilled(false);

        controls.add(trash);
        row.add(controls, BorderLayout.CENTER);

        JLabel p = new JLabel(price);
        p.setFont(new Font("SansSerif", Font.BOLD, 21));
        row.add(p, BorderLayout.EAST);

        return row;
    }

    private JPanel summaryLine(String name, String price) {
        JPanel line = new JPanel(new BorderLayout());
        line.setOpaque(false);

        JLabel a = new JLabel(name);
        a.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel b = new JLabel(price);
        b.setFont(new Font("SansSerif", Font.BOLD, 18));

        line.add(a, BorderLayout.WEST);
        line.add(b, BorderLayout.EAST);

        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        return line;
    }

    private void showCheckoutDialog() {
        JDialog dialog = new JDialog(frame, "Pago", true);

        dialog.setSize(760, 390);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(BaseFrame.VERDE);

        JLabel t = new JLabel("Finalizar compra");
        t.setForeground(Color.WHITE);
        t.setFont(BaseFrame.FONT_BOLD);
        t.setBorder(new EmptyBorder(8, 14, 8, 14));
        top.add(t, BorderLayout.WEST);

        JButton close = frame.iconButton("×");
        close.addActionListener(e -> dialog.dispose());
        top.add(close, BorderLayout.EAST);

        dialog.add(top, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2));
        content.setBackground(BaseFrame.FONDO);

        JPanel user = formPanel("Ingrese sus datos:");

        addField(user, "Ingrese nombre");
        addField(user, "Ingrese dirección");
        addField(user, "Ingrese número telefónico");

        JPanel payment = formPanel("Información de pago:");

        addField(payment, "Número de tarjeta");
        addField(payment, "Fecha de vencimiento");
        addField(payment, "CVV");

        payment.add(Box.createVerticalStrut(12));

        JButton pay = frame.roundedButton(
                "PAGAR",
                BaseFrame.VERDE_CLARO,
                BaseFrame.VERDE
        );

        pay.setAlignmentX(Component.CENTER_ALIGNMENT);
        pay.setMaximumSize(new Dimension(140, 38));

        payment.add(pay);

        user.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 0, 1, Color.BLACK),
                new EmptyBorder(25, 30, 25, 30)
        ));

        payment.setBorder(new EmptyBorder(25, 30, 25, 30));

        content.add(user);
        content.add(payment);

        dialog.add(content, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private JPanel formPanel(String title) {
        JPanel p = new JPanel();

        p.setBackground(BaseFrame.FONDO);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(label);
        p.add(Box.createVerticalStrut(18));

        return p;
    }

    private void addField(JPanel parent, String placeholder) {
        JTextField field = new JTextField(placeholder);

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setPreferredSize(new Dimension(300, 36));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(Color.GRAY);

        field.setBorder(new CompoundBorder(
                new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(6, 10, 6, 10)
        ));


        // ARREGLA EL PROBLEMA DE TENER QUE BORRAR ANTES DE ESCRIBIR, SE BORRA AL HACER CLICK

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        parent.add(field);
        parent.add(Box.createVerticalStrut(13));
    }
}
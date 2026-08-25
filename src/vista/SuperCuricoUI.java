package vista;
// Importamos Swing
import javax.swing.*;
import javax.swing.border.*;
// Clases de diseño, colores, fuentes, dimensiones y dibujo.
import java.awt.*;
// Eventos
import java.awt.event.*;

/**
 * Interfaz Java 
 * Solo UI y navegación básica entre pantallas
 * A ARREGLAR: BOTONES INICIO CON LAS IMAGENES COMO BOTONES, TAMBIÉN AGREGAR IMAGENES (LOGO, FOTOS, ETC.)
 */
public class SuperCuricoUI extends JFrame {

    // La clase hereda de JFrame (Es la clase principal)

    // Colores y fuentes como en el mockup (a revisar, no estoy segura)
    private static final Color VERDE = new Color(46, 103, 57);
    private static final Color VERDE_CLARO = new Color(166, 211, 160);
    private static final Color FONDO = Color.WHITE;
    private static final Font FONT_NORMAL = new Font("SansSerif", Font.PLAIN, 15);
    private static final Font FONT_BOLD = new Font("SansSerif", Font.BOLD, 16);
    private static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 28);

    // CardLayout permite tener varias "pantallas" dentro de una sola ventana (Gracias google)
    // y mostrar una u otra según la navegación del usuario.
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel root = new JPanel(cardLayout);

    // Constructor: configura la ventana y registra las pantallas principales.
    public SuperCuricoUI() {
        super("SuperCuricó");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 720));
        setSize(1180, 780);
        setLocationRelativeTo(null);

        // Cada panel se agrega con un nombre para luego poder mostrarlo.
        root.add(createHomePanel(), "INICIO");
        root.add(createProductsPanel(), "PRODUCTOS");
        root.add(createCartPanel(), "CARRO");

        setContentPane(root);
        // Pantalla que se muestra al iniciar el programa.
        cardLayout.show(root, "INICIO");
    }

    // Crea la pantalla de inicio: encabezado, banner, categorías...
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.add(createHeader(false, () -> cardLayout.show(root, "CARRO")), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(FONDO);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(new EmptyBorder(30, 80, 40, 80));

        JPanel banner = new JPanel(new BorderLayout());
        banner.setMaximumSize(new Dimension(700, 140));
        banner.setPreferredSize(new Dimension(700, 140));
        banner.setBackground(VERDE_CLARO);
        banner.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel bannerText = new JLabel("<html><div style='text-align:center;'>Verduras frescas<br><b>TODOS LOS DÍAS</b></div></html>", SwingConstants.CENTER);
        bannerText.setFont(FONT_TITLE);
        bannerText.setForeground(new Color(255, 236, 60));
        banner.add(bannerText, BorderLayout.CENTER);
        center.add(banner);
        center.add(Box.createVerticalStrut(35));

        JPanel categories = new JPanel(new GridLayout(1, 4, 35, 0));
        categories.setOpaque(false);
        categories.setMaximumSize(new Dimension(820, 170));

        categories.add(createCategory("FRUTAS", new Color(244, 176, 65)));
        categories.add(createCategory("LÁCTEOS", new Color(235, 235, 235)));
        categories.add(createCategory("PANADERÍA", new Color(195, 139, 87)));
        categories.add(createCategory("CARNES", new Color(222, 145, 145)));

        center.add(categories);
        center.add(Box.createVerticalGlue());

        JButton enter = roundedButton("VER PRODUCTOS", VERDE_CLARO, VERDE);
        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.setMaximumSize(new Dimension(220, 45));
        // Al hacer clic, CardLayout cambia desde el inicio a productos
        enter.addActionListener(e -> cardLayout.show(root, "PRODUCTOS"));
        center.add(enter);

        panel.add(center, BorderLayout.CENTER);
        return panel;
    }

    // Crea el catálogo de productos en una cuadrícula de 2 filas x 5 columnas (igual al mockup, a ajustar igual)
    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.add(createHeader(true, () -> cardLayout.show(root, "CARRO")), BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 5, 28, 28));
        grid.setBackground(FONDO);
        grid.setBorder(new EmptyBorder(30, 55, 35, 55));

        grid.add(createProductCard("Plátano", "$1.490", new Color(246, 220, 67)));
        grid.add(createProductCard("Manzana", "$1.190", new Color(187, 62, 55)));
        grid.add(createProductCard("Naranja", "$990", new Color(242, 150, 47)));
        grid.add(createProductCard("Durazno", "$1.390", new Color(238, 139, 65)));
        grid.add(createProductCard("Frutilla", "$1.690", new Color(216, 56, 68)));
        grid.add(createProductCard("Lechuga", "$790", new Color(115, 181, 77)));
        grid.add(createProductCard("Tomate", "$990", new Color(219, 63, 53)));
        grid.add(createProductCard("Zanahoria", "$890", new Color(239, 132, 38)));
        grid.add(createProductCard("Cebolla", "$850", new Color(204, 153, 104)));
        grid.add(createProductCard("Brócoli", "$990", new Color(65, 133, 69)));

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    // Crea la pantalla del carrito
    // Por ahora los productos y el total están escritos nomas
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.add(createCartHeader(), BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2));
        content.setBackground(FONDO);

        JPanel left = new JPanel();
        left.setBackground(FONDO);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(30, 55, 30, 45));

        left.add(createCartRow("Plátano", "$1.490", new Color(246, 220, 67)));
        left.add(Box.createVerticalStrut(20));
        left.add(createCartRow("Lechuga", "$790", new Color(115, 181, 77)));
        left.add(Box.createVerticalStrut(20));
        left.add(createCartRow("Brócoli", "$990", new Color(65, 133, 69)));

        JPanel right = new JPanel();
        right.setBackground(FONDO);
        right.setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));
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

        JButton pagar = roundedButton("PAGAR", VERDE_CLARO, VERDE);
        pagar.setAlignmentX(Component.CENTER_ALIGNMENT);
        pagar.setMaximumSize(new Dimension(160, 44));
        // Abre la ventana modal con datos del cliente y pago
        pagar.addActionListener(e -> showCheckoutDialog());
        right.add(pagar);

        content.add(left);
        content.add(right);
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    // Encabezado 
    // withBack indica si el botón de menú funciona como "volver". (gracias google de nuevo)
    // cartAction indica qué hacer al pulsar el carrito
    private JPanel createHeader(boolean withBack, Runnable cartAction) {
        JPanel header = new JPanel(new BorderLayout(20, 0));
        header.setBackground(VERDE);
        header.setBorder(new EmptyBorder(12, 28, 12, 28));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 0));
        left.setOpaque(false);

        JButton menu = iconButton("☰");
        if (withBack) {
            menu.setToolTipText("Volver");
            // vuelve al inicio
            menu.addActionListener(e -> cardLayout.show(root, "INICIO"));
        }
        left.add(menu);

        JLabel logo = new JLabel("◉");
        logo.setFont(new Font("SansSerif", Font.BOLD, 36));
        logo.setForeground(Color.WHITE);
        left.add(logo);
        header.add(left, BorderLayout.WEST);

        JTextField search = new JTextField("Buscar en SuperCuricó");
        search.setFont(FONT_NORMAL);
        search.setForeground(new Color(120, 120, 120));
        search.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(7, 18, 7, 18)
        ));
        header.add(search, BorderLayout.CENTER);

        JButton cart = iconButton("🛒");
        cart.setFont(new Font("SansSerif", Font.PLAIN, 28));
        cart.setToolTipText("Carro");
        // Ejecuta la acción recibida como parámetro
        cart.addActionListener(e -> cartAction.run());
        header.add(cart, BorderLayout.EAST);

        return header;
    }

    // Encabezado para la pantalla del carrito
    private JPanel createCartHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(VERDE);
        header.setBorder(new EmptyBorder(10, 28, 10, 28));

        JButton back = iconButton("☰");
        back.addActionListener(e -> cardLayout.show(root, "PRODUCTOS"));
        header.add(back, BorderLayout.WEST);

        JLabel title = new JLabel("Mi carrito", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setForeground(VERDE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(6, 28, 6, 28));
        header.add(title, BorderLayout.CENTER);

        JButton cart = iconButton("🛒");
        cart.setFont(new Font("SansSerif", Font.PLAIN, 28));
        header.add(cart, BorderLayout.EAST);
        return header;
    }

    // Componente reutilizable para representar una categoría
    private JPanel createCategory(String name, Color c) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel circle = new JPanel();
        circle.setBackground(c);
        circle.setPreferredSize(new Dimension(115, 115));
        circle.setMaximumSize(new Dimension(115, 115));
        circle.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));
        circle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(name);
        label.setFont(FONT_BOLD);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(circle);
        p.add(Box.createVerticalStrut(8));
        p.add(label);
        return p;
    }

    // Crea una ventana emerjente de producto con nombre, precio y botón Agregar
    private JPanel createProductCard(String name, String price, Color productColor) {
        JPanel card = new JPanel();
        card.setBackground(FONDO);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(8, 8, 8, 8));

        JPanel product = new JPanel();
        product.setBackground(productColor);
        product.setMaximumSize(new Dimension(125, 90));
        product.setPreferredSize(new Dimension(125, 90));
        product.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));
        product.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(FONT_BOLD);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(FONT_NORMAL);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton add = roundedButton("Agregar", VERDE_CLARO, VERDE);
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setMaximumSize(new Dimension(120, 32));
        // Al hacer clic en Agregar se abre el coso de poner cantidad (no funciona aún)
        add.addActionListener(e -> showQuantityDialog(name, productColor));

        card.add(product);
        card.add(Box.createVerticalStrut(7));
        card.add(nameLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(add);
        return card;
    }

    // Crea una fila visual del carrito con cantidad, botón eliminar y precio
    // El botón de basura todavía no funciona
    private JPanel createCartRow(String name, String price, Color productColor) {
        JPanel row = new JPanel(new BorderLayout(18, 0));
        row.setBackground(FONDO);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 115));

        JPanel product = new JPanel();
        product.setPreferredSize(new Dimension(120, 90));
        product.setBackground(productColor);
        product.setBorder(new LineBorder(new Color(225, 225, 225), 1, true));
        row.add(product, BorderLayout.WEST);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 28));
        controls.setOpaque(false);
        JComboBox<String> qty = new JComboBox<>(new String[]{"0,5", "1", "1,5", "2"});
        qty.setPreferredSize(new Dimension(95, 32));
        controls.add(qty);

        JButton trash = new JButton("🗑");
        trash.setFont(new Font("SansSerif", Font.PLAIN, 22));
        trash.setForeground(VERDE);
        trash.setBorderPainted(false);
        trash.setContentAreaFilled(false);
        controls.add(trash);
        row.add(controls, BorderLayout.CENTER);

        JLabel p = new JLabel(price);
        p.setFont(new Font("SansSerif", Font.BOLD, 21));
        row.add(p, BorderLayout.EAST);
        return row;
    }

    // resumen de compra nombre, precio 
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

    // Ventana modal para elegir la cantidad de un producto. (Gracias google x3)
    // Es modal porque bloquea la ventana principal hasta cerrarse
    private void showQuantityDialog(String productName, Color productColor) {
        JDialog dialog = new JDialog(this, "Agregar producto", true);
        dialog.setSize(470, 260);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(VERDE);
        JLabel title = new JLabel(productName);
        title.setForeground(Color.WHITE);
        title.setFont(FONT_BOLD);
        title.setBorder(new EmptyBorder(8, 14, 8, 14));
        top.add(title, BorderLayout.WEST);
        JButton close = iconButton("×");
        close.addActionListener(e -> dialog.dispose());
        top.add(close, BorderLayout.EAST);
        dialog.add(top, BorderLayout.NORTH);

        JPanel body = new JPanel();
        body.setBackground(FONDO);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(18, 25, 18, 25));

        JPanel visual = new JPanel();
        visual.setBackground(productColor);
        visual.setMaximumSize(new Dimension(120, 70));
        visual.setPreferredSize(new Dimension(120, 70));
        visual.setAlignmentX(Component.CENTER_ALIGNMENT);
        body.add(visual);
        body.add(Box.createVerticalStrut(14));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        row.setOpaque(false);
        JLabel label = new JLabel("Ingrese Unidades");
        label.setOpaque(true);
        label.setBackground(VERDE_CLARO);
        label.setBorder(new EmptyBorder(7, 10, 7, 10));
        JTextField units = new JTextField("0", 4);
        JComboBox<String> step = new JComboBox<>(new String[]{"0,5", "1", "1,5", "2"});
        row.add(label);
        row.add(units);
        row.add(step);
        body.add(row);
        body.add(Box.createVerticalStrut(16));

        JButton add = roundedButton("Agregar", VERDE_CLARO, VERDE);
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setMaximumSize(new Dimension(120, 35));
        // En esta versión solo cierra el diálogo; aún no guarda nada
        add.addActionListener(e -> dialog.dispose());
        body.add(add);

        dialog.add(body, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    // Checkout datos del comprador  y datos de pago
    //Solo interfaz
    private void showCheckoutDialog() {
        JDialog dialog = new JDialog(this, "Pago", true);
        dialog.setSize(760, 390);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(VERDE);
        JLabel t = new JLabel("Finalizar compra");
        t.setForeground(Color.WHITE);
        t.setFont(FONT_BOLD);
        t.setBorder(new EmptyBorder(8, 14, 8, 14));
        top.add(t, BorderLayout.WEST);
        JButton close = iconButton("×");
        close.addActionListener(e -> dialog.dispose());
        top.add(close, BorderLayout.EAST);
        dialog.add(top, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2));
        content.setBackground(FONDO);

        JPanel user = formPanel("Ingrese sus datos:");
        addField(user, "Ingrese nombre");
        addField(user, "Ingrese dirección");
        addField(user, "Ingrese número telefónico");

        JPanel payment = formPanel("Información de pago:");
        addField(payment, "Número de tarjeta");
        addField(payment, "Fecha de vencimiento");
        addField(payment, "CVV");
        payment.add(Box.createVerticalStrut(12));
        JButton pay = roundedButton("PAGAR", VERDE_CLARO, VERDE);
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

    // Crea un panel vertical reutilizable 
    private JPanel formPanel(String title) {
        JPanel p = new JPanel();
        p.setBackground(FONDO);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(label);
        p.add(Box.createVerticalStrut(18));
        return p;
    }

    // Agrega un JTextField al panel recibido
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
        parent.add(field);
        parent.add(Box.createVerticalStrut(13));
    }

    // Crea un botón con esquinas redondeadas dibujándolo manualmente
    private JButton roundedButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text) {
            @Override
            // Sobrescribimos paintComponent para pintar primero el fondo redondeado
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
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
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Crea botones sin fondo para los iconos
    private JButton iconButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.PLAIN, 26));
        b.setForeground(Color.WHITE);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    // Punto de entrada
    public static void main(String[] args) {
        // Swing debe crear y actualizar la interfaz
        SwingUtilities.invokeLater(() -> {
            try {
                // Intenta usar la apariencia visual del sistema operativo (Gracias google again xd)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            // Crea la ventana y la hace visible
            new SuperCuricoUI().setVisible(true);
        });
    }
}

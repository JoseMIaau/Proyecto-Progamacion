package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VistaProductos extends JPanel {

    private final BaseFrame frame;

    public VistaProductos(BaseFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(BaseFrame.FONDO);

        add(createHeader(), BorderLayout.NORTH);

        JPanel grid = new JPanel(
                new GridLayout(2, 5, 28, 28)
        );

        grid.setBackground(BaseFrame.FONDO);

        grid.setBorder(
                new EmptyBorder(
                        30, 55, 35, 55
                )
        );

        grid.add(
                createProductCard(
                        "Plátano",
                        "$1.490",
                        new Color(246, 220, 67)
                )
        );

        grid.add(
                createProductCard(
                        "Manzana",
                        "$1.190",
                        new Color(187, 62, 55)
                )
        );

        grid.add(
                createProductCard(
                        "Naranja",
                        "$990",
                        new Color(242, 150, 47)
                )
        );

        grid.add(
                createProductCard(
                        "Durazno",
                        "$1.390",
                        new Color(238, 139, 65)
                )
        );

        grid.add(
                createProductCard(
                        "Frutilla",
                        "$1.690",
                        new Color(216, 56, 68)
                )
        );

        grid.add(
                createProductCard(
                        "Lechuga",
                        "$790",
                        new Color(115, 181, 77)
                )
        );

        grid.add(
                createProductCard(
                        "Tomate",
                        "$990",
                        new Color(219, 63, 53)
                )
        );

        grid.add(
                createProductCard(
                        "Zanahoria",
                        "$890",
                        new Color(239, 132, 38)
                )
        );

        grid.add(
                createProductCard(
                        "Cebolla",
                        "$850",
                        new Color(204, 153, 104)
                )
        );

        grid.add(
                createProductCard(
                        "Brócoli",
                        "$990",
                        new Color(65, 133, 69)
                )
        );

        add(grid, BorderLayout.CENTER);
    }

    private JPanel createHeader() {

        JPanel header = new JPanel(
                new BorderLayout(20, 0)
        );

        header.setBackground(BaseFrame.VERDE);

        header.setBorder(
                new EmptyBorder(
                        12, 28, 12, 28
                )
        );

        JPanel left = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        18,
                        0
                )
        );

        left.setOpaque(false);

        JButton menu = frame.iconButton("☰");

        menu.setToolTipText("Volver");

        menu.addActionListener(
                e -> frame.mostrarVista("INICIO")
        );

        left.add(menu);

        JLabel logo = new JLabel("◉");

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        36
                )
        );

        logo.setForeground(Color.WHITE);

        left.add(logo);

        header.add(left, BorderLayout.WEST);

        JTextField search =
                new JTextField("Buscar en SuperCuricó");

        search.setFont(BaseFrame.FONT_NORMAL);
        search.setForeground(
                new Color(120, 120, 120)
        );

        search.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(220, 220, 220),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                7, 18, 7, 18
                        )
                )
        );

        header.add(search, BorderLayout.CENTER);

        JButton cart = frame.iconButton("🛒");

        cart.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        28
                )
        );

        cart.setToolTipText("Carro");

        cart.addActionListener(
                e -> frame.mostrarVista("CARRO")
        );

        header.add(cart, BorderLayout.EAST);

        return header;
    }

    private JPanel createProductCard(
            String name,
            String price,
            Color productColor
    ) {

        JPanel card = new JPanel();

        card.setBackground(BaseFrame.FONDO);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        8, 8, 8, 8
                )
        );

        JPanel product = new JPanel();

        product.setBackground(productColor);

        product.setMaximumSize(
                new Dimension(125, 90)
        );

        product.setPreferredSize(
                new Dimension(125, 90)
        );

        product.setBorder(
                new LineBorder(
                        new Color(230, 230, 230),
                        1,
                        true
                )
        );

        product.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel nameLabel = new JLabel(name);

        nameLabel.setFont(BaseFrame.FONT_BOLD);

        nameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel priceLabel = new JLabel(price);

        priceLabel.setFont(BaseFrame.FONT_NORMAL);

        priceLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton add = frame.roundedButton(
                "Agregar",
                BaseFrame.VERDE_CLARO,
                BaseFrame.VERDE
        );

        add.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        add.setMaximumSize(
                new Dimension(120, 32)
        );

        add.addActionListener(
                e -> showQuantityDialog(
                        name,
                        productColor
                )
        );

        card.add(product);
        card.add(Box.createVerticalStrut(7));
        card.add(nameLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(add);

        return card;
    }

    private void showQuantityDialog(
            String productName,
            Color productColor
    ) {

        JDialog dialog = new JDialog(
                frame,
                "Agregar producto",
                true
        );

        dialog.setSize(470, 260);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JPanel top = new JPanel(
                new BorderLayout()
        );

        top.setBackground(BaseFrame.VERDE);

        JLabel title = new JLabel(productName);

        title.setForeground(Color.WHITE);

        title.setFont(BaseFrame.FONT_BOLD);

        title.setBorder(
                new EmptyBorder(
                        8, 14, 8, 14
                )
        );

        top.add(title, BorderLayout.WEST);

        JButton close = frame.iconButton("×");

        close.addActionListener(
                e -> dialog.dispose()
        );

        top.add(close, BorderLayout.EAST);

        dialog.add(top, BorderLayout.NORTH);

        JPanel body = new JPanel();

        body.setBackground(BaseFrame.FONDO);

        body.setLayout(
                new BoxLayout(
                        body,
                        BoxLayout.Y_AXIS
                )
        );

        body.setBorder(
                new EmptyBorder(
                        18, 25, 18, 25
                )
        );

        JPanel visual = new JPanel();

        visual.setBackground(productColor);

        visual.setMaximumSize(
                new Dimension(120, 70)
        );

        visual.setPreferredSize(
                new Dimension(120, 70)
        );

        visual.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        body.add(visual);
        body.add(Box.createVerticalStrut(14));

        JPanel row = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        12,
                        0
                )
        );

        row.setOpaque(false);

        JLabel label =
                new JLabel("Ingrese Unidades");

        label.setOpaque(true);

        label.setBackground(
                BaseFrame.VERDE_CLARO
        );

        label.setBorder(
                new EmptyBorder(
                        7, 10, 7, 10
                )
        );

        JTextField units =
                new JTextField("0", 4);

        JComboBox<String> step =
                new JComboBox<>(
                        new String[]{
                            "0,5",
                            "1",
                            "1,5",
                            "2"
                        }
                );

        row.add(label);
        row.add(units);
        row.add(step);

        body.add(row);
        body.add(Box.createVerticalStrut(16));

        JButton add = frame.roundedButton(
                "Agregar",
                BaseFrame.VERDE_CLARO,
                BaseFrame.VERDE
        );

        add.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        add.setMaximumSize(
                new Dimension(120, 35)
        );

        add.addActionListener(
                e -> dialog.dispose()
        );

        body.add(add);

        dialog.add(
                body,
                BorderLayout.CENTER
        );

        dialog.setVisible(true);
    }
}
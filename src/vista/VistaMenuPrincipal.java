package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VistaMenuPrincipal extends JPanel {

    private final BaseFrame frame;

    public VistaMenuPrincipal(BaseFrame frame) {

        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(BaseFrame.FONDO);

        add(createHeader(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(BaseFrame.FONDO);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(new EmptyBorder(30, 80, 40, 80));

        JPanel banner = new JPanel(new BorderLayout());

        banner.setMaximumSize(new Dimension(700, 140));
        banner.setPreferredSize(new Dimension(700, 140));
        banner.setBackground(BaseFrame.VERDE_CLARO);

        banner.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel bannerText = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Verduras frescas<br>"
                + "<b>TODOS LOS DÍAS</b>"
                + "</div></html>",
                SwingConstants.CENTER
        );

        bannerText.setFont(BaseFrame.FONT_TITLE);
        bannerText.setForeground(new Color(255, 236, 60));

        banner.add(bannerText, BorderLayout.CENTER);

        center.add(banner);
        center.add(Box.createVerticalStrut(35));

        JPanel categories = new JPanel(
                new GridLayout(1, 4, 35, 0)
        );

        categories.setOpaque(false);
        categories.setMaximumSize(
                new Dimension(820, 170)
        );

        categories.add(
                createCategory(
                        "FRUTAS",
                        new Color(244, 176, 65)
                )
        );

        categories.add(
                createCategory(
                        "LÁCTEOS",
                        new Color(235, 235, 235)
                )
        );

        categories.add(
                createCategory(
                        "PANADERÍA",
                        new Color(195, 139, 87)
                )
        );

        categories.add(
                createCategory(
                        "CARNES",
                        new Color(222, 145, 145)
                )
        );

        center.add(categories);
        center.add(Box.createVerticalGlue());

        JButton enter = frame.roundedButton(
                "VER PRODUCTOS",
                BaseFrame.VERDE_CLARO,
                BaseFrame.VERDE
        );

        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.setMaximumSize(new Dimension(220, 45));

        enter.addActionListener(
                e -> frame.mostrarVista("PRODUCTOS")
        );

        center.add(enter);

        add(center, BorderLayout.CENTER);
    }

    private JPanel createHeader() {

        JPanel header = new JPanel(
                new BorderLayout(20, 0)
        );

        header.setBackground(BaseFrame.VERDE);
        header.setBorder(
                new EmptyBorder(12, 28, 12, 28)
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
        search.setForeground(new Color(120, 120, 120));

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

    private JPanel createCategory(
            String name,
            Color color
    ) {

        JPanel p = new JPanel();

        p.setOpaque(false);

        p.setLayout(
                new BoxLayout(
                        p,
                        BoxLayout.Y_AXIS
                )
        );

        JPanel circle = new JPanel();

        circle.setBackground(color);

        circle.setPreferredSize(
                new Dimension(115, 115)
        );

        circle.setMaximumSize(
                new Dimension(115, 115)
        );

        circle.setBorder(
                new LineBorder(
                        new Color(230, 230, 230),
                        1,
                        true
                )
        );

        circle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel label = new JLabel(name);

        label.setFont(BaseFrame.FONT_BOLD);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        p.add(circle);
        p.add(Box.createVerticalStrut(8));
        p.add(label);

        return p;
    }
}
import javax.swing.*;
import java.awt.*;

public class ProductCard extends JPanel {
    public ProductCard(Product product) {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setPreferredSize(new Dimension(180, 220));
        setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon(product.imagePath);
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH)));

        JLabel nameLabel = new JLabel("<html><b>" + product.name + "</b></html>");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel brandLabel = new JLabel(product.brand);
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        brandLabel.setForeground(Color.GRAY);

        JLabel priceLabel = new JLabel("$" + product.price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel noteLabel = new JLabel("<html><i>" + product.note + "</i></html>");
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        noteLabel.setForeground(Color.GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(noteLabel);
        infoPanel.add(brandLabel);
        infoPanel.add(priceLabel);

        add(imageLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
    }
}

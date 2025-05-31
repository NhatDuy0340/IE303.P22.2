import javax.swing.*;
import java.awt.*;

public class ProductDetailPanel extends JPanel {
    public ProductDetailPanel(Product product) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon(product.imagePath);
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH)));

        JLabel nameLabel = new JLabel("<html><h2>" + product.name + "</h2></html>");
        JLabel priceLabel = new JLabel("<html><h3>$" + product.price + "</h3></html>");
        JLabel brandLabel = new JLabel(product.brand);
        JLabel noteLabel = new JLabel("<html><i>" + product.note + "</i></html>");

        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        noteLabel.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.setOpaque(false);
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);
        textPanel.add(brandLabel);
        textPanel.add(noteLabel);

        add(imageLabel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductListPanel extends JPanel {
    public ProductListPanel(List<Product> products) {
        int columnCount = 4;
        int rowCount = (int) Math.ceil(products.size() / (double) columnCount);

        setLayout(new GridLayout(rowCount, columnCount, 10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Product product : products) {
            add(new ProductCard(product));
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Product Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        Product mainProduct = new Product("4DFWD PULSE SHOES", 160.00, "Adidas",
                "This product is excluded from all promotional discounts and offers.",
                "images/img1.png"); // Đường dẫn hình ảnh

        List<Product> productList = new ArrayList<>();
        productList.add(mainProduct);
        productList.add(new Product("FORUM MID SHOES", 100.00, "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img2.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img3.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img4.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img5.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img6.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img1.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img2.png"));
        productList.add(new Product("SUPERNOVA SHOES", 150.00, "Adidas", "NMD City Stock 2", "images/img3.png"));
        // ... thêm sản phẩm tùy ý

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ProductDetailPanel(mainProduct),
                new JScrollPane(new ProductListPanel(productList),
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        splitPane.setDividerLocation(300);
        add(splitPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}

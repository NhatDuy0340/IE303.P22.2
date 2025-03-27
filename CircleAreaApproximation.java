import java.util.Random;

public class CircleAreaApproximation {
    // Tính diện tích hình tròn bằng phương pháp Monte Carlo
    // Sử dụng số ngẫu nhiên để xác định xem điểm có nằm trong hình tròn hay không.
    public static double approximateArea(double r, int iterations) {
        Random random = new Random();
        int insideCircle = 0;
        // Tạo số ngẫu nhiên trong khoảng [-r, r] cho cả x và y
        // và kiểm tra xem điểm (x, y) có nằm trong hình tròn hay không.
        for (int i = 0; i < iterations; i++) {
            double x = (2 * r) * random.nextDouble() - r;
            double y = (2 * r) * random.nextDouble() - r;
            
            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        // Tính diện tích hình tròn bằng công thức A = π * r^2
        // Sử dụng tỷ lệ số điểm nằm trong hình tròn so với tổng số điểm
        return 4.0 * r * r * ((double) insideCircle / iterations);
    }
    
    public static void main(String[] args) {
        double r = 6.0; // Ví dụ bán kính
        int iterations = 40000000; // Số lần lặp để tăng độ chính xác
        System.out.println("Diện tích xấp xỉ của hình tròn: " + approximateArea(r, iterations));
    }
}

import java.util.Random;

public class CircleAreaApproximation2 {
    public static double approximateArea(double r, int iterations) {
        Random random = new Random();
        int insideCircle = 0;
        
        for (int i = 0; i < iterations; i++) {
            double x = (2 * r) * random.nextDouble() - r;
            double y = (2 * r) * random.nextDouble() - r;
            
            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        
        return 4.0 * r * r * ((double) insideCircle / iterations);
    }
    
    public static void main(String[] args) {
        double r = 1.0; // Bán kính bằng 1 sẽ ra được số pi
        int iterations = 40000000; // Số lần lặp để tăng độ chính xác
        System.out.println("Diện tích xấp xỉ của hình tròn: " + approximateArea(r, iterations));
    }
}

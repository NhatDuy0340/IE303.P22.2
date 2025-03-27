import java.util.Arrays;
import java.util.Stack;

// Định nghĩa một điểm trong không gian 2D
class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Bai3 {

    static Point p0;

    // Trả về điểm kế tiếp trên đỉnh của stack mà không làm thay đổi stack.
    static Point nextToTop(Stack<Point> stk) {
        Point p = stk.pop();
        Point res = stk.peek();
        stk.push(p);
        return res;
    }

    // Hoán đổi vị trí của hai điểm trong mảng.
    static void swap(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    // Tính bình phương khoảng cách giữa hai điểm p1 và p2.
    static int distSq(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) +
               (p1.y - p2.y) * (p1.y - p2.y);
    }

    // Xác định hướng của ba điểm p, q, r.
    // Trả về 0 nếu ba điểm collinear, 1 nếu theo chiều kim đồng hồ, 2 nếu ngược chiều kim đồng hồ.
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) -
                  (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // collinear
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }

    // So sánh hai điểm p1 và p2 dựa trên góc với điểm p0.
    // Nếu p1 và p2 collinear với p0, chọn điểm xa nhất từ p0.
    static int compare(Point p1, Point p2) {
        int o = orientation(p0, p1, p2);
        if (o == 0) {
            return (distSq(p0, p2) >= distSq(p0, p1)) ? -1 : 1;
        }
        return (o == 2) ? -1 : 1;
    }

    // Tìm tập hợp các điểm tạo thành bao lồi (convex hull) từ mảng các điểm.
    // Sử dụng thuật toán Graham's scan.
    static void convexHull(Point[] points, int n) {
        // Tìm điểm có tọa độ y nhỏ nhất (và x nhỏ nhất nếu có nhiều điểm cùng y)
        int min = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].y < points[min].y ||
                (points[i].y == points[min].y && points[i].x < points[min].x)) {
                min = i;
            }
        }

        // Đặt điểm có tọa độ y nhỏ nhất ở đầu mảng
        swap(points, 0, min);
        p0 = points[0];

        // Sắp xếp các điểm còn lại theo góc với p0
        // Sử dụng hàm compare để sắp xếp theo thứ tự tăng dần
        Arrays.sort(points, 1, n, GfG::compare);

        // Loại bỏ các điểm collinear
        // Chỉ giữ lại điểm đầu tiên trong số các điểm collinear
        int m = 1;
        for (int i = 1; i < n; i++) {
            while (i < n - 1 && orientation(p0, points[i],
                                            points[i + 1]) == 0) {
                i++;
            }
            points[m] = points[i];
            m++;
        }

        // Nếu số lượng điểm sau khi loại bỏ collinear < 3, không thể tạo thành bao lồi
        if (m < 3) return;

        // Khởi tạo stack và thêm ba điểm đầu tiên vào stack
        // để bắt đầu quá trình tạo bao lồi
        Stack<Point> stk = new Stack<>();
        stk.push(points[0]);
        stk.push(points[1]);
        stk.push(points[2]);

        // Duyệt qua các điểm còn lại và xây dựng bao lồi
        for (int i = 3; i < m; i++) {
            while (stk.size() > 1 && orientation(nextToTop(stk), 
                                            stk.peek(), points[i]) != 2) {
                stk.pop();
            }
            stk.push(points[i]);
        }

        // In ra các điểm trong bao lồi theo thứ tự ngược lại
        while (!stk.isEmpty()) {
            Point p = stk.pop();
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }

    // Hàm main để chạy chương trình
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Nhap so luong diem: ");   // Nhập số lượng điểm
        int n = scanner.nextInt();

        Point[] points = new Point[n]; 
        // Nhập tọa độ cho từng điểm
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap toa do x, y cua diem " + (i + 1) + ": ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        // In ra các điểm đã nhập
        convexHull(points, n);
    }
}

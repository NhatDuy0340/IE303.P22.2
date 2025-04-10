import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class gameFlappyBird extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 360, HEIGHT = 640; // Kích thước của cửa sổ game
    private static final int PIPE_WIDTH = 80, PIPE_GAP = 150;   // Chiều rộng và khoảng cách giữa 2 ống
    private static final int BIRD_SIZE = 40;    // Kích thước của chim
    private static final int PIPE_ADD_INTERVAL = 90;    // Khoảng cách giữa các ống mới được thêm vào

    private Timer timer;
    private int birdY = HEIGHT / 2, birdVelocity = 0, score = 0;    // Tọa độ chim và vận tốc rơi
    private boolean gameOver = false;   // Trạng thái game (đang chơi hay đã kết thúc)
    private int pipeDistanceCounter = 0;

    private final ArrayList<Rectangle> pipes = new ArrayList<>();
    private Image background, bird, topPipe, bottomPipe;

    // Constructor: thiết lập kích thước, sự kiện bàn phím và khởi tạo game
    public gameFlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        loadImages();
        addPipe();
        timer = new Timer(20, this);
        timer.start();
    }

    // Load hình ảnh cho background, chim và ống
    private void loadImages() {
        background = new ImageIcon("flappybirdbg.png").getImage(); 
        bird = new ImageIcon("flappybird.png").getImage();
        topPipe = new ImageIcon("toppipe.png").getImage();
        bottomPipe = new ImageIcon("bottompipe.png").getImage();
    }

    // Thêm ống vào game
    // Ống trên và dưới sẽ được thêm vào danh sách pipes
    private void addPipe() {
        int pipeHeight = 50 + (int) (Math.random() * (HEIGHT - PIPE_GAP - 200)); // Chiều cao ngẫu nhiên cho ống trên
        int bottomPipeY = pipeHeight + PIPE_GAP;    // Chiều cao ống dưới = chiều cao ống trên + khoảng cách giữa 2 ống
        if (bottomPipeY + 50 > HEIGHT) {
            pipeHeight -= (bottomPipeY + 50 - HEIGHT); 
        } 
        pipes.add(new Rectangle(WIDTH, 0, PIPE_WIDTH, pipeHeight));     // thêm ống trên
        pipes.add(new Rectangle(WIDTH, pipeHeight + PIPE_GAP, PIPE_WIDTH, HEIGHT - pipeHeight - PIPE_GAP)); //thêm ống dưới
    }

    // Xử lý sự kiện bàn phím
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            timer.stop(); // Dừng game khi game over
            return;
        }

        birdY += birdVelocity;
        birdVelocity += 1;
        
        pipeDistanceCounter++;
        // Cập nhật vị trí các ống
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            pipe.x -= 5;

            if (pipe.x + PIPE_WIDTH < 0) {
            if (pipe.y == 0) score++; // Chỉ tính điểm cho ống trên
            pipes.remove(i--);
            }

        }
        // Thêm ống mới sau mỗi khoảng thời gian nhất định
        // Nếu khoảng cách giữa các ống >= PIPE_ADD_INTERVAL thì thêm ống mới
        if (pipeDistanceCounter >= PIPE_ADD_INTERVAL) {
            addPipe();
            pipeDistanceCounter = 0;
        }

        Rectangle birdRect = new Rectangle(WIDTH / 4, birdY, BIRD_SIZE, BIRD_SIZE);
        for (Rectangle pipe : pipes) {
            if (pipe.intersects(birdRect)) {
                gameOver = true;
            }
        }

        if (birdY < 0 || birdY + BIRD_SIZE > HEIGHT) {
            gameOver = true;
        }

        repaint();
    }

    // Vẽ các thành phần của game: background, chim, ống và điểm số
    // Nếu game over thì hiển thị thông báo game over
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(bird, WIDTH / 4, birdY, BIRD_SIZE, BIRD_SIZE, null);

        for (Rectangle pipe : pipes) {
            if (pipe.y == 0) {
                g.drawImage(topPipe, pipe.x, pipe.y, pipe.width, pipe.height, null);
            } else {
                g.drawImage(bottomPipe, pipe.x, pipe.y, pipe.width, pipe.height, null);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Điểm: " + score, 10, 30);

        if (gameOver) {
            String msg = "Game Over - Nhấn SPACE để chơi lại";
            Font font = new Font("Arial", Font.BOLD, 16);
            FontMetrics fm = g.getFontMetrics(font);
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            int y = HEIGHT / 2;
            g.setFont(font);
            g.drawString(msg, x, y);
        }
    }

    // Xử lý sự kiện bàn phím: nhấn phím SPACE để nhảy hoặc bắt đầu lại game
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                birdVelocity = -10;
            } else {
                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // khởi động lại game
    // Đặt lại các biến và xóa các ống đã tồn tại
    private void restartGame() {
        birdY = HEIGHT / 2;
        birdVelocity = 0;
        score = 0;
        pipes.clear();
        pipeDistanceCounter = 0;
        gameOver = false;
        addPipe();
        timer.start();
    }

    // Tạo cửa sổ game và khởi động game
    // Tạo một JFrame và thêm gameFlappyBird vào nó
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        gameFlappyBird game = new gameFlappyBird();
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {

    private RenderPanel renderPanel;

    ArrayList<Point> snakeParts = new ArrayList<>();

    static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score = 0, tailLength = 10;

    private Random random;

    protected Point head, cherry;

    private Dimension dim;

    static Snake snake;
    boolean running = true, paused = false;
    public Timer timer = new Timer(20, this);

    private Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 700);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame();
    }
        public void startGame() {
            running = true;
            paused = false;
            ticks = 0;
            score = 0;
            tailLength = 10;
            direction = DOWN;
            head = new Point(0, 0);
            snakeParts.clear();
            random = new Random();
            cherry = new Point(random.nextInt(78), random.nextInt(65));
            for (int i = 0; i < tailLength; i++) {
                snakeParts.add(new Point(head.x, head.y));
            }
            timer.start();
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        if (running && !paused){
            ticks++;

        if (ticks % 2 == 0 && head != null) {
            snakeParts.add(new Point(head.x, head.y));
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    running = false;
                }
            }
            if (direction == DOWN) {
                if (head.y + 1 <= 64 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    running = false;
                }
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    running = false;
                }
            }
            if (direction == RIGHT) {
                if (head.x - 1 <= 77 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    running = false;
                }
            }
            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);
            }
            if (cherry != null) {
                if (head.equals(cherry)) {
                    score++;
                    tailLength++;
                    cherry.setLocation(random.nextInt(78), random.nextInt(65));
                }
            }
        }
        }
    }
    public boolean noTailAt(int x, int y){
        for (Point point : snakeParts)
        {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        snake = new Snake();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT && direction != RIGHT)
        {
            direction = LEFT;
        }
        if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT && direction != LEFT)
        {
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP && direction != DOWN)
        {
            direction = UP;
        }
        if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN && direction != UP)
        {
            direction = DOWN;
        }
        if(i == KeyEvent.VK_SPACE){
            if (!running)
                startGame();
            else
                paused = !paused;
        }
        if(i == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package Snake;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel{

    private static int curColor = 0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(curColor));
        g.fillRect(0,0,800,700);
        Snake snake = Snake.snake;
        for (Point point : snake.snakeParts)
        {
            g.setColor(Color.WHITE);
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        g.setColor(Color.RED);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        g.setColor(Color.white);
        String string = String.format("Score: %s \n Snake length: %s \n Time: %s", snake.score, snake.tailLength, snake.ticks / 50);
        g.drawString(string, 500,20);
    }
}
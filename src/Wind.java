import java.awt.Color;
import java.awt.Graphics;

public class Wind implements WeatherEffect {

    @Override
    public void apply(Cell cell, Graphics g, double value) {
        // Draw small arrows to indicate wind strength
        int arrowLength = (int) (10 + 10 * value);
        g.setColor(new Color(180, 180, 255));
        int centerX = cell.x + cell.width / 2;
        int centerY = cell.y + cell.height / 2;
        g.drawLine(centerX, centerY, centerX + arrowLength, centerY);
    }
}

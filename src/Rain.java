import java.awt.Color;
import java.awt.Graphics;

public class Rain implements WeatherEffect {

    @Override
    public void apply(Cell cell, Graphics g, double value) {
        // Darken the cell based on rainfall intensity (value between 0 and 1)
        int shade = (int) (200 * (1 - value)); // higher rain = darker
        g.setColor(new Color(0, 0, shade));
        g.fillRect(cell.x + 2, cell.y + 2, cell.width - 4, cell.height - 4);
    }
}

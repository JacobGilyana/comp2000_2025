import java.awt.Color;
import java.awt.Graphics;

public class Heat implements WeatherEffect {

    @Override
    public void apply(Cell cell, Graphics g, double value) {
        // Make cell redder with increasing temperature
        int intensity = (int) (255 * value);
        g.setColor(new Color(intensity, 50, 0)); // reddish-orange hue
        g.fillRect(cell.x + 2, cell.y + 2, cell.width - 4, cell.height - 4);
    }
}

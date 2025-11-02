import java.awt.Graphics;

public interface WeatherEffect {
    void apply(Cell cell, Graphics g, double value);
}

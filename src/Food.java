import java.awt.Color;
import java.awt.Graphics;

public class Food extends Item implements Collectible, Usable {
  private int nutrition;

  public Food(String name, int nutrition) {
    super(name);
    this.nutrition = nutrition;
  }

  @Override
  public void onCollect() {
    System.out.println(name + " collected!");
  }

  @Override
  public void use() {
    System.out.println("You eat " + name + " and gain " + nutrition + " energy!");
  }

  @Override
  public void paint(Graphics g, int x, int y) {
    g.setColor(Color.RED);
    g.fillOval(x + 10, y + 10, 15, 15);
    g.setColor(Color.BLACK);
    g.drawOval(x + 10, y + 10, 15, 15);
  }
}
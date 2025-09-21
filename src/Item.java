import java.awt.Color;
import java.awt.Graphics;

public abstract class Item {
  protected String name;

  public Item(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void paint(Graphics g, int x, int y);
}

interface Collectible {
  void onCollect();
}

interface Usable {
  void use();
}

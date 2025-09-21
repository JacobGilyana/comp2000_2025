import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
  Color color;
  Cell loc;
  List<Polygon> display;
  List<Item> inventory = new ArrayList<>();

  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  public void collectItem() {
    if(loc.item != null) {
      Item it = loc.item;
      if(it instanceof Collectible) {
        ((Collectible) it).onCollect();
      }
      inventory.add(it);
      loc.item = null; // remove from grid
    }
  }

  public void useItem(int index) {
    if(index >= 0 && index < inventory.size()) {
      Item it = inventory.get(index);
      if(it instanceof Usable) {
        ((Usable) it).use();
      }
    }
  }
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;
<<<<<<< HEAD
=======
  Item item;
>>>>>>> 0f710ff2dd9fa393389ed1fa55f1d9e95ce91adb

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

<<<<<<< HEAD
  public void paint(Graphics g, Point mousePos) {
    if(contains(mousePos)) {
      g.setColor(Color.GRAY);
    } else {
      g.setColor(Color.WHITE);
    }
    g.fillRect(x, y, size, size);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, size, size);
  }

  @Override
=======
public void paint(Graphics g, Point mousePos) {
  if(contains(mousePos)) {
    g.setColor(Color.GRAY);
  } else {
    g.setColor(Color.WHITE);
  }
  g.fillRect(x, y, size, size);
  g.setColor(Color.BLACK);
  g.drawRect(x, y, size, size);

  // Draw item if present
  if(item != null) {
    item.paint(g, x, y);
  }
}

>>>>>>> 0f710ff2dd9fa393389ed1fa55f1d9e95ce91adb
  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }
<<<<<<< HEAD

  public int leftOfComparison(Cell c) {
    return Integer.compare(col, c.col);
  }

  public int aboveComparison(Cell c) {
    return Integer.compare(row, c.row);
  }
=======
>>>>>>> 0f710ff2dd9fa393389ed1fa55f1d9e95ce91adb
}
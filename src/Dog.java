import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
<<<<<<< HEAD
  public static final int dogMoves = 1;

  public Dog(Cell inLoc, boolean isBot) {
    super(inLoc, Color.YELLOW, isBot, dogMoves);
  }

  protected void setPoly() {
=======
  public Dog(Cell inLoc) {
    loc = inLoc;
    color = Color.YELLOW;
>>>>>>> 0f710ff2dd9fa393389ed1fa55f1d9e95ce91adb
    display = new ArrayList<Polygon>();
    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 5, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 5);
    ear1.addPoint(loc.x + 5, loc.y + 15);
    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 20, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 15);
    Polygon face = new Polygon();
    face.addPoint(loc.x + 8, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 25);
    face.addPoint(loc.x + 8, loc.y + 25);
    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }
}
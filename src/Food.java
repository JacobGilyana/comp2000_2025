import java.awt.Color;
import java.awt.Graphics;

public class Food extends Item implements Collectible, Usable {
    private final int nutrition;

    public Food(String name, int nutrition) {
        super(name);
        this.nutrition = nutrition;
    }

    @Override
    public void onCollect() {
        System.out.println(getName() + " collected!");
    }

    @Override
    public void use() {
        System.out.println("Eating " + getName() + " restores " + nutrition + " health.");
    }

    @Override
    public void paint(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillOval(x + 10, y + 10, 10, 10); // small green dot
    }
}
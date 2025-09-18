import java.awt.Graphics;

public abstract class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void paint(Graphics g, int x, int y);

}


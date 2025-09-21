import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        if (item != null) items.add(item);
    }

    public void use(int index) {
        if (index < 0 || index >= items.size()) return;
        T item = items.get(index);
        if (item instanceof Usable) {
            ((Usable) item).use();
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<T> getItems() {
        return items;
    }
}

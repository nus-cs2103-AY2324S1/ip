package rat.storage;
import java.util.ArrayList;
import rat.print.RatPrinter;

public class RatStorage<T> {

    private ArrayList<T> storage;
    private RatPrinter ratPrinter = new RatPrinter();

    public RatStorage() {
        storage = new ArrayList<T>();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.storage.size(); i++) {
            str += (i + 1) + ". " + this.storage.get(i) + "\n";
        }
        return str;
    }

    public void addItem(T item) {
        this.storage.add(item);
        this.ratPrinter.printWithLines("added: " + item);
    }

    public void deleteItem(int index) {
        this.ratPrinter.printWithLines("deleted: " + storage.get(index));
        this.storage.remove(index);
    }

    public void listItems() {
        ratPrinter.printWithLines(this.toString());
    }
}
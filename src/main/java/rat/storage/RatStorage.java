package rat.storage;
import java.util.ArrayList;
import rat.print.RatPrinter;

public class RatStorage<T> {

    private ArrayList<T> storage;
    private RatPrinter ratPrinter = new RatPrinter();

    public RatStorage() {
        storage = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.storage.size(); i++) {
            str += (i + 1) + ". " + this.storage.get(i).toString() + "\n";
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

    public void markItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            this.ratPrinter.printWithLines("invalid index");
            return;
        } else if (this.storage.get(index - 1) == null) {
            this.ratPrinter.printWithLines("item not found");
            return;
        }
        T item = this.storage.get(index - 1);
        if (item instanceof Task) {
            ((Task) item).markDone();
        }
        this.ratPrinter.printWithLines("Nice! I've marked this task as done: " + storage.get(index - 1).toString());
    }

    public void unmarkItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            this.ratPrinter.printWithLines("invalid index");
            return;
        } else if (this.storage.get(index - 1) == null) {
            this.ratPrinter.printWithLines("item not found");
            return;
        }
        T item = this.storage.get(index - 1);
        if (item instanceof Task) {
            ((Task) item).unmarkDone();
        }
        this.ratPrinter.printWithLines("Ok, I've marked this task as not done yet: " + storage.get(index - 1).toString());
    }

    public void listItems() {
        ratPrinter.printWithLines(this.toString());
    }
}
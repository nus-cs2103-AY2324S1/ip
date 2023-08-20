import java.util.ArrayList;
import java.lang.StringBuilder;

public class ItemList {
    private ArrayList<Item> list;

    public ItemList() {
        this.list = new ArrayList<>();
    }

    public Item addItem(String name) {
        Item item = new Item(name);
        this.list.add(item);
        return item;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.list.size();
        if (len == 0) {
            return "The item list is empty.";
        }
        for (int i = 1; i < len; i++) {
            str.append(i + ". " + this.list.get(i-1) + "\n");
        }
        str.append(len + ". " + this.list.get(len-1));
        return str.toString();
    }

}

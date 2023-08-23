import java.util.ArrayList;

/**
 * State used to read and interact with the application state
 */
public class DukeState {
    private ArrayList<String> items = new ArrayList<>();

    /**
     * Insert an item into the list of items.
     *
     * @param item The item to be added.
     */
    public void insertItem(String item) {
        items.add(item);
    }

    /**
     * List out all the items
     */
    public void listItems() {
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            System.out.printf("\t %d. %s%n", i + 1, item);
        }
        System.out.println("\t" + DukeConstants.HORIZONTAL_LINE);
    }
}

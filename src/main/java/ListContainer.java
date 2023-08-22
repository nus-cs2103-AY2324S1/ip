import java.util.ArrayList;

/**
 * Stores the list of items that the user has.
 */
public class ListContainer {
    private static ArrayList<ListItem> list = new ArrayList<>();

    /**
     * Adds an item to the list.
     *
     * @param name The name of the item
     */
    public void addToList(String name) {
        list.add(new ListItem(name));

        System.out.println("added: " + name);
    }

    /**
     * Removes an item from the list.
     *
     * @param name The name of the item to remove
     */
    public void removeFromList(String name) {
        list.removeIf(item -> item.getItemName().equals(name));
    }

    /**
     * Marks a specific task in the list as done.
     * Indexes start from 1, not 0
     *
     * @param index
     */
    public void markAsDone(int index) {
        list.get(index - 1).setDone();
    }

    @Override
    public String toString() {
        StringBuilder resultMsg = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            resultMsg.append(i + 1).append(". ").append(list.get(i).toString());

            resultMsg.append("\n");
        }

        return resultMsg.toString();
    }

}

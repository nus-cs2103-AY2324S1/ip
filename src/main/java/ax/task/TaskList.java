package ax.task;


import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * contains an ArrayList of ListItem to be used
     */
    static List<ListItem> listItems = new ArrayList<ListItem>();

    /**
     * gets the list of items
     * @return List<ListItem> containing all items
     */

    public static List<ListItem> getListItems() {
        return listItems;
    }
}
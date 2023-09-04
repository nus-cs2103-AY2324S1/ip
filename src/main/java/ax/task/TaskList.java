package ax.task;


import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class contains the list of tasks
 */
public class TaskList {
    /**
     * contains an ArrayList of ListItem to be used
     */
    private static List<ListItem> listItems = new ArrayList<ListItem>();

    /**
     * gets the list of items
     * @return a List of ListItem containing all items
     */

    public static List<ListItem> getListItems() {
        return listItems;
    }
}

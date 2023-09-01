package ax.task;

import ax.task.ListItem;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<ListItem> listItems = new ArrayList<ListItem>();

    public static List<ListItem> getListItems() {
        return listItems;
    }
}
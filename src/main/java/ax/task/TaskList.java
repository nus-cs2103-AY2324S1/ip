package ax.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<ListItem> listItems = new ArrayList<>();

    public static List<ListItem> getListItems() {
        return listItems;
    }
}
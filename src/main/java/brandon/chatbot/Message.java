package brandon.chatbot;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    public static String showTasks(TaskList tasks) {
        final ArrayList<String> formattedTasks = new ArrayList<>();
        for (Task t : tasks.getList()) {
            formattedTasks.add(t.getStatus());
        }
        return getIndexedListForViewing(formattedTasks);
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 1;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}

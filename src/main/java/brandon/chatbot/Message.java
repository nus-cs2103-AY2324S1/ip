package brandon.chatbot;

import java.util.ArrayList;
import java.util.List;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Represents a class with methods that convert task list into a String.
 */
public class Message {
    private static final String LIST_MESSAGE = "Here is the current list of tasks...:\n";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /**
     * Converts tasks in the task list to a String value.
     * Method adapted from se-edu addressbook-2.
     * @param tasks
     * @return
     */
    public static String showTasks(TaskList tasks) {
        final ArrayList<String> formattedTasks = new ArrayList<>();
        for (Task t : tasks.getList()) {
            formattedTasks.add(t.getStatus());
        }
        return LIST_MESSAGE + getIndexedListForViewing(formattedTasks);
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

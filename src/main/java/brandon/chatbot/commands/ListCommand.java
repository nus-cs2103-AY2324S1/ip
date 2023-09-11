package brandon.chatbot.commands;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command that lists the tasks in TaskList.
 */
public class ListCommand extends Command {
    public static final String LIST_SUCCESS = "ok... I'm listing..";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    public String showTasks(TaskList tasks) {
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

    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_SUCCESS + "\n" + showTasks(tasks));
    }
}

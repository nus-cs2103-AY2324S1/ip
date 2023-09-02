package duke.message;

import java.util.ArrayList;

import duke.task.Task;
import duke.templates.MessageTemplates;

/**
 * Represents the FindTaskMessage.
 */
public class FindTaskMessage extends Message {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for FindTaskMessage.
     * @param tasks Tasks to be found.
     */
    public FindTaskMessage(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates the message for FindTaskMessage.
     * @param list ArrayList of Tasks.
     * @return String representation of FindTaskMessage.
     */
    private String[] formatList(ArrayList<Task> list) {
        String[] ls = new String[list.size() + 1];
        ls[0] = MessageTemplates.MESSAGE_FOUND_TASKS;
        int index = 1;
        for (Task item: list) {
            ls[index] = String.format("%d. %s", index, item.toString());
            index++;
        }
        return ls;
    }
    /**
     * Returns String representation of FindTaskMessage.
     */
    @Override
    public String send() {
        return createMessage(formatList(this.tasks));
    }
}

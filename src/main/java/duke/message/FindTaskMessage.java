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
        String[] ls = new String[list.size()];
        int index = 1;
        for (Task item: list) {
            ls[index - 1] = String.format("%d. %s", index, item.toString());
            index++;
        }
        return ls;
    }

    /**
     * Prints FindTaskMessage.
     */
    @Override
    public void send() {
        System.out.println(MessageTemplates.MESSAGE_FOUND_TASKS);
        System.out.println(
                createMessage(
                        formatList(this.tasks)
                )
        );
    }
}

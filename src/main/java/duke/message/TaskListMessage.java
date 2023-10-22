package duke.message;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the TaskListMessage.
 */
public class TaskListMessage extends Message {
    private final String[] list;

    /**
     * Calls constructor for TaskListMessage.
     * @param list ArrayList of Tasks.
     */
    public TaskListMessage(ArrayList<Task> list) {
        this.list = formatList(list);
    }

    /**
     * Creates the message for TaskListMessage.
     * @param list ArrayList of Tasks.
     * @return String representation of TaskListMessage.
     */
    private String[] formatList(ArrayList<Task> list) {
        String[] tasks = new String[list.size()];
        int index = 1;
        for (Task item: list) {
            tasks[index - 1] = String.format("%d. %s", index, item.toString());
            index++;
        }
        return tasks;
    }

    /**
     * Prints TaskListMessage.
     */
    @Override
    public String send() {
        return createMessage(list);
    }
}

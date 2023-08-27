package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

/**
 * Represents the AddTaskMessage.
 */
public class AddTaskMessage extends Message {
    private final Task task;
    private final int totalTasks;
    /**
     * Constructor for AddTaskMessage.
     * @param task Task to be added.
     * @param totalTasks Total number of tasks.
     */
    public AddTaskMessage(Task task, int totalTasks) {
        this.task = task;
        this.totalTasks = totalTasks;
    }
    /**
     * Prints AddTaskMessage.
     */
    @Override
    public void send() {
        System.out.println(
                createMessage(
                    MessageTemplates.MESSAGE_ADDED_TASK,
                    this.task.toString(),
                    String.format(MessageTemplates.MESSAGE_NUMBER_OF_TASKS, this.totalTasks)
                )
        );
    }
}

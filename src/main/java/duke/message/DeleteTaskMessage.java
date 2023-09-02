package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

/**
 * Represents the DeleteTaskMessage.
 */
public class DeleteTaskMessage extends Message {
    private final Task task;
    private final int totalTasks;

    /**
     * Constructor for DeleteTaskMessage.
     * @param task Task to be deleted.
     * @param totalTasks Total number of tasks.
     */
    public DeleteTaskMessage(Task task, int totalTasks) {
        this.task = task;
        this.totalTasks = totalTasks;
    }

    /**
     * Returns String representation of DeleteTaskMessage.
     */
    @Override
    public String send() {
        return createMessage(
                MessageTemplates.MESSAGE_REMOVED_TASK,
                this.task.toString(),
                String.format(MessageTemplates.MESSAGE_NUMBER_OF_TASKS, this.totalTasks)
        );
    }
}

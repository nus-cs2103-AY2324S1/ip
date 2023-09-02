package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

/**
 * Represents the MarkTaskMessage.
 */
public class MarkTaskMessage extends Message {
    private final Task task;

    /**
     * Constructor for MarkTaskMessage.
     * @param task Task to be marked.
     */
    public MarkTaskMessage(Task task) {
        this.task = task;
    }
    /**
     * Returns String representation of MarkTaskMessage and marks task as done.
     */
    @Override
    public String send() {
        task.markTask();
        return createMessage(
                MessageTemplates.MESSAGE_MARK_DONE,
                this.task.toString()
        );
    }
}

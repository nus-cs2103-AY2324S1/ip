package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

/**
 * Represents the UnmarkTaskMessage.
 */
public class UnmarkTaskMessage extends Message {
    private final Task task;

    /**
     * Constructor for UnmarkTaskMessage.
     * @param task Task to be unmarked.
     */
    public UnmarkTaskMessage(Task task) {
        this.task = task;
    }

    /**
     * Returns String representation of UnmarkTaskMessage and unmarks task as done.
     */
    @Override
    public String send() {
        task.unmarkTask();
        return createMessage(
                MessageTemplates.MESSAGE_MARK_NOT_DONE,
                this.task.toString()
        );
    }
}

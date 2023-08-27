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
     * Prints UnmarkTaskMessage.
     */
    @Override
    public void send() {
        task.unmarkTask();
        System.out.println(
                createMessage(
                        MessageTemplates.MESSAGE_MARK_NOT_DONE,
                        this.task.toString()
                )
        );
    }
}

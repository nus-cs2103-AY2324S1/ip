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
     * Prints MarkTaskMessage.
     */
    @Override
    public void send() {
        task.markTask();
        System.out.println(createMessage(MessageTemplates.MESSAGE_MARK_DONE, this.task.toString()));
    }
}

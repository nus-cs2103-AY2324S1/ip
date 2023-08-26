package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

public class UnmarkTaskMessage extends Message {
    private final Task task;
    public UnmarkTaskMessage(Task task) {
        this.task = task;
    }
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

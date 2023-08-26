package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

public class MarkTaskMessage extends Message {
    private final Task task;
    public MarkTaskMessage(Task task) {
        this.task = task;
    }
    @Override
    public void send() {
        task.markTask();
        System.out.println(createMessage(MessageTemplates.MESSAGE_MARK_DONE, this.task.toString()));
    }
}

package duke.message;

import duke.task.Task;

public class UnmarkTaskMessage extends Message {
    private final Task task;
    public UnmarkTaskMessage(Task task) {
        this.task = task;
    }
    @Override
    public void send() {
        String message = "OK, I've marked this task as not done yet:";
        task.unmarkTask();
        System.out.println(createMessage(message, this.task.toString(), horizontalLine));
    }
}

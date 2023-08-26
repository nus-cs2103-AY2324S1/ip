package duke.message;

import duke.task.Task;

public class MarkTaskMessage extends Message {
    private final Task task;
    public MarkTaskMessage(Task task) {
        this.task = task;
    }
    @Override
    public void send() {
        String message = "Nice! I've marked this task as done:";
        task.markTask();
        System.out.println(createMessage(message, this.task.toString(), horizontalLine));
    }
}

package duke.message;

import duke.task.Task;

public class AddTaskMessage extends Message {
    private final Task task;
    private final int totalTasks;
    public AddTaskMessage(Task task, int totalTasks) {
        this.task = task;
        this.totalTasks = totalTasks;
    }
    @Override
    public void send() {
        System.out.println(
                createMessage(
                    "Got it. I've added this task:",
                    this.task.toString(),
                    String.format("Now you have %d tasks in the list.", this.totalTasks),
                    horizontalLine
                )
        );
    }
}

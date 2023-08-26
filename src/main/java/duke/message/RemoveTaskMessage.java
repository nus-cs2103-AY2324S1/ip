package duke.message;

import duke.task.Task;

public class RemoveTaskMessage extends Message {
    private final Task task;
    private final int totalTasks;
    public RemoveTaskMessage(Task task, int totalTasks) {
        this.task = task;
        this.totalTasks = totalTasks;
    }
    @Override
    public void send() {
        System.out.println(
                createMessage(
                        "Noted. I've removed this task:",
                        this.task.toString(),
                        String.format("Now you have %d tasks in the list.", this.totalTasks),
                        horizontalLine
                )
        );
    }
}

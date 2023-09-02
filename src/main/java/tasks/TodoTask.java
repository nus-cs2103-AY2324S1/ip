package tasks;

import tasks.Task;

public class TodoTask extends Task {
    public TodoTask(String taskName) {
        super(taskName, TaskType.TODO);
    }
}

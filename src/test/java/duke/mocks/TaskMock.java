package duke.mocks;

import duke.Task;
public class TaskMock extends Task {
    private final String taskName;

    public TaskMock(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    @Override
    public String serialize() {
        return "SERIALIZED";
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}

package duke.mocks;

import duke.task.Task;

/**
 * Mock Task class.
 */
public class TaskMock extends Task {
    private final String taskName;

    /**
     * Initialises mock task.
     * @param taskName Name of mock task.
     */
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

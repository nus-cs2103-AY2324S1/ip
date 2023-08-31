package duke.task;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a ToDo task in the Duke application.
 * Todos tasks have no time restrictions
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given task description.
     *
     * @param task The description of the ToDo task.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Constructs a ToDo task with the given task description and completion status.
     *
     * @param task The description of the ToDo task.
     * @param isDone The completion status of the ToDo task.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "T|" + (this.isDone ? "1|" : "0|") + this.getTask();
        fw.write(storedRow);
    }
}

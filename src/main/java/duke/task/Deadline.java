package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents a deadline task in the Duke application.
 * Deadline tasks have a specific due date.
 */
public class Deadline extends Task {
    private String due;

    /**
     * Constructs a Deadline task with the given task description, completion status, and due date.
     *
     * @param task The description of the deadline task.
     * @param isDone The completion status of the deadline task.
     * @param due The due date of the deadline task.
     * @throws DateTimeException If there is an issue with date or time formatting.
     */
    public Deadline(String task, boolean isDone, String due) throws DateTimeException {
        super(task, isDone);
        this.due = due;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "D|" + (this.isDone ? "1|" : "0|") + this.getTask() + "|" + this.due;
        fw.write(storedRow);
    }
}

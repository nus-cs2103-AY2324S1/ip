package task;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a Task that a User can see in a TaskList.
 */
public class Task {
    /** The description detailing the Task. */
    protected String description;
    /** Describes whether the Task is completed. */
    protected boolean isDone;

    /**
     * Constructs a Task with a description and is set to be not done.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Takes in a String that was read through a .txt file and wraps in a Task object.
     * @param line
     * @return a Task
     * @throws IOException
     */
    public static Task fromString(String line) throws IOException {
        if (line.startsWith("T")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = new ToDo(description);
            task.toggleIsDone(isCompleted);

            return task;
        } else if (line.startsWith("D")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();
            String by = parts[3].trim();
            LocalDate date = LocalDate.parse(by);

            Task task = new Deadline(description, date);
            task.toggleIsDone(isCompleted);

            return task;
        } else if (line.startsWith("E")) {
            String[] parts = line.split("\\|");
            boolean isCompleted = parts[1].trim().equals("1");
            String description = parts[2].trim();
            String[] time = parts[3].trim().split("-");

            Task task = new Event(description, time[0].trim(), time[1].trim());
            task.toggleIsDone(isCompleted);

            return task;
        } else {
            throw new IOException("Corrupted File. What you doin' bruh...");
        }
    }

    /**
     * Sets the Task isDone boolean to be isDone.
     * @param isDone
     */
    public void toggleIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of the isDone of the Task.
     * @return a String representation
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a String representation of the Task.
     * @return a String representation
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a String representation of the Task for the .txt file.
     * @return a String representation
     */
    public String toFileString() {
        return description;
    }
}

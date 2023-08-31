package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of the task logic
 */
public class Task {

    /**
     * Formatter to output date time
     */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("EEE hh:mma, MMM yyyy");
    /**
     * Separator used
     */
    public static final String SEP = "#";
    /**
     * The description of the task
     */
    private String task;
    /**
     * The state of the task
     */
    private boolean isComplete = false;
    /**
     * Constructor for the duke.task.Task class
     *
     * @param task - the description of the task created
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Format data according to current format
     *
     * @param time - the local date time to format
     * @return formatted string according to the format
     */
    public static String formatDate(LocalDateTime time) {
        return Task.OUTPUT_FORMAT.format(time);
    }

    /**
     * Accessor for the completed field
     *
     * @return true if completed is true
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Toggles the complete field
     */
    public void toggleComplete() {
        this.isComplete = !this.isComplete;
    }

    /**
     * Getter for tasks
     *
     * @return task string
     */
    public String getTask() {
        return task;
    }

    /**
     * returns the stored form of the task
     *
     * @return a string which duke.Parser could Parse
     */
    public String getStored() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", this.task);
    }

    /**
     * Checks if it is the exact same TASK
     *
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task task = (Task) other;
            return this.getStored().equals(task.getStored());
        }
        return false;
    }
}

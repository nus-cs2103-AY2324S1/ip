package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the task logic
 */
public abstract class Task {

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
     * Constructs a task instance
     *
     * @param task - the description of the task created
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Formats data according to current format
     *
     * @param time - the local date time to format
     * @return formatted string according to the format
     */
    public static String formatDate(LocalDateTime time) {
        return Task.OUTPUT_FORMAT.format(time);
    }

    /**
     * Returns the isComplete field
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
     * Returns the task description
     *
     * @return task string
     */
    public String getTask() {
        return task;
    }

    /**
     * Returns the storage form of the task
     *
     * @return a string which duke Parser could Parse
     */
    public abstract String getStored();

    /**
     * Returns the stored form of the task
     *
     * @param days - the range of days of task to be reminded
     * @return true if this task need to be reminded
     */
    public abstract boolean isRemind(int days);


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

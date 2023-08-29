import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    /**
     * Formatter to output date time
     */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("EEE hh:mma, MMM yyyy");
    /**
     * Separator used
     */
    public static String SEP = "#";

    /**
     * The description of the task
     */
    private String task;

    /**
     * The state of the task
     */
    private boolean completed = false;

    /**
     * Constructor for the Task class
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
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Toggles the complete field
     */
    public void toggleCompleted() {
        this.completed = !this.completed;
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
     * @return a string which Duke Parser could Parse
     */
    public String getStored() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", this.task);
    }
}

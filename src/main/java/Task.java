import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstract class that represents a task.
 */
public abstract class Task {

    // The description of the task.
    private String description;

    // Whether the task is done.
    private boolean isDone;

    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm");

    /**
     * Creates a new Task object.
     * 
     * @param input The description of the task.
     */
    public Task(String input) {
        this.description = input;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract String getSaveFormat();

    public static String parseDateInput(String input) throws DukeInvalidArgumentException {
        try {
            return LocalDateTime.parse(input, parseFormatter).format(parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                    "Your date seems to be formatted wrongly. Please follow this format: yyyy-MM-dd HH:mm.");
        }
    }

    public static String getDateOutputString(String input) {
        return LocalDateTime.parse(input, parseFormatter).format(outputFormatter);
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}

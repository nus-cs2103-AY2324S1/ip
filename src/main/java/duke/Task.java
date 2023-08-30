package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class Task {
    /**
     * name of task
     */
    private String taskName;
    /**
     * boolean to track whether the task has been marked as done
     */
    private boolean done;

    private static final String DATETIME_OUTPUT_FORMAT = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT);

    /**
     * Constructor for the task class
     * tasks are initialised as false
     */
    public Task(String name) {
        this.taskName = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public abstract String toSaveStateString();

    public boolean getDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean containsKeyword(String keyword) {
        if (this.taskName.contains(keyword)) {
            return true;
        }
        return false;
    }

    public abstract boolean isOnDate(LocalDate date);

    public static String[] processInput(String[] splitInput) throws InvalidTaskException {
        String joined = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length));
        return joined.split(" /");
    }
}

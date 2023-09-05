package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Task class represents a basic task with a description and completion status.
 */
public class Task {
    private Boolean done = false;

    private String task;

    public Task(String input) {
        task = input;
    }

    private String type;

    private String desc;

    private String checkBox;

    private String deadline;

    public void markDone() {
        done = true;
    }

    /**
     * Checks if the task matches a given description.
     *
     * @param desc The description to compare.
     * @return True if the task's description matches the given description; otherwise, false.
     */
    public boolean filterMatchDesc(String desc) {
        String[] splitsy = this.toString().split(" \\| ");
        System.out.println(splitsy[2].trim());
        return splitsy[2].trim().equals(desc.trim());
    }


    public void markUndone() {
        done = false;
    }

    protected boolean isDone() {
        return done;
    }

    public String toString() {
        if (done) {
            return "[X] " + "| " + task;
        } else {
            return "[ ] " +"| " + task;
        }
    }

}

package DukePackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Task class represents a task with a description, status, type, start time, and end time.
 */
public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task (done or not done).
     */
    protected boolean isDone;

    /**
     * The type of the task (TODO, DEADLINE, or EVENT).
     */
    protected TaskType type;

    /**
     * The start time of the task.
     */
    protected LocalDateTime start;

    /**
     * The end time of the task.
     */
    protected LocalDateTime end;

    /**
     * The string displayed when the task is marked as done.
     */
    String markString = "    Nice! I've marked this task as done:";

    /**
     * The string displayed when the task is marked as not done yet.
     */
    String unmarkString = "     OK, I've marked this task as not done yet:";

    /**
     * Constructs a Task object with the given description, type, start time, and end time.
     *
     * @param description The description of the task.
     * @param type        The type of the task.
     * @param start       The start time of the task.
     * @param end         The end time of the task.
     */
    public Task(String description, TaskType type, String start, String end) {
        this.description = description;
        this.isDone = false;
        // set to-do as the default type
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.start = Objects.equals(start, "")
                ? null
                : LocalDateTime.parse(start, formatter);
        this.end = Objects.equals(end, "")
                ? null
                : LocalDateTime.parse(end, formatter);
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the type icon of the task.
     *
     * @return The type icon of the task.
     */
    public String getTypeIcon() {
        switch (this.type) {
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";
        }
        return null;
    }

    /**
     * Sets the status of the task.
     *
     * @param checked The new status of the task.
     */
    public void marking(Boolean checked) {
        this.isDone = checked;
    }

    /**
     * Prints the description of the task.
     */
    public void descriptionString() {
        String initStatement = "     Got it. I've added this task:";
        System.out.println(initStatement);
        this.printMarking(false);
    }

    /**
     * Prints the marking of the task.
     *
     * @param mark Indicates whether to display the marking or not.
     */
    public void printMarking(boolean mark) {
        if (mark) {
            if (this.isDone) {
                System.out.println(markString);
            } else {
                System.out.println(unmarkString);
            }
        }
        System.out.printf("       [%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.description);
        if (!Objects.isNull(this.start) && !Objects.isNull(this.end)) {
            System.out.printf(" (from: %s to: %s)", this.start.toString().replace("T", " "), this.end.toString().replace("T", " "));
        } else if (!Objects.isNull(this.start)) {
            System.out.printf(" (by: %s)", this.start.toString().replace("T", " "));
        } else {
            return;
        }
    }
}
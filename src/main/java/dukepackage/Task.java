package dukepackage;

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
    protected LocalDateTime startTime;

    /**
     * The end time of the task.
     */
    protected LocalDateTime endTime;

    /**
     * The string displayed when the task is marked as done.
     */
    protected final String MARKED_STRING = "    Nice! I've marked this task as done:\n";

    /**
     * The string displayed when the task is marked as not done yet.
     */
    protected final String UNMARKED_STRING = "     OK, I've marked this task as not done yet:\n";

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = Objects.equals(start, "")
                ? null
                : LocalDateTime.parse(start, formatter);
        this.endTime = Objects.equals(end, "")
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
    public void setStatus(Boolean checked) {
        this.isDone = checked;
    }

    /**
     * Prints the description of the task.
     */
    public String printDescription() {
        String initStatement = "     Got it. I've added this task:\n";
//        System.out.println(initStatement);
        return initStatement + this.printMarking(false);
    }

    /**
     * Prints the marking of the task.
     *
     * @param mark Indicates whether to display the marking or not.
     */
    public String printMarking(boolean mark) {
        String result = "";
        if (mark) {
            if (this.isDone) {
                result += MARKED_STRING;
            } else {
                result += UNMARKED_STRING;
            }
        }
        result += String.format("       [%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.description);
        if (!Objects.isNull(this.startTime) && !Objects.isNull(this.endTime)) {
            String start = this.startTime.toString().replace("T", " ");
            String end = this.endTime.toString().replace("T", " ");
            result += String.format(" (from: %s to: %s)", start, end);
        } else if (!Objects.isNull(this.startTime)) {
            String start = this.startTime.toString().replace("T", " ");
            result += String.format(" (by: %s)", start);
        } else {
            result += "";
        }
        return result;
    }
}
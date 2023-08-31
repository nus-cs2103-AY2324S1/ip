package harvard;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String type;

    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description The description of the task.
     */

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }
    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * Returns the string representation of the task to be written to file.
     * @return The string representation of the task to be written to file.
     */

    public abstract String toFileString() ;

    /**
     * Returns the task from the file string.
     * @param fileString
     * @return The task from the file string.
     * @throws DukeException
     */

    public static Task fromFileString(String fileString) throws DukeException{
        String[] split = fileString.split(" \\| ");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task;
        switch (type) {
        case "T":
            task = new Todo (description);
            break;
        case "D":
            String by = split[3];
            LocalDateTime dateTime = LocalDateTime.parse(by,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            task = new Deadline(description, dateTime);
            break;
        case "E":
            String from = split[3];
            String to = split[4];
            LocalDateTime fromDateTime = LocalDateTime.parse(from,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime toDateTime = LocalDateTime.parse(to,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            task = new Event(description, fromDateTime, toDateTime);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

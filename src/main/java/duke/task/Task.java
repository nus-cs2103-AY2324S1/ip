package duke.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    /** Represents a task in the task list */
    protected String description;

    /** Indicates whether the task is done or not */
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     * The initial completion status is set to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task ("[X]" if done, "[ ]" if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns a formatted string representation of the Task object.
     *
     * @return A formatted string representation of the Task object.
     */
    public String toFormattedString() {
        return "";
    }

    /**
     * Creates a Task object from a formatted string representation.
     *
     * @param formattedString The formatted string representation of the Task object.
     * @return The Task object created from the formatted string.
     */
    public static Task createTaskFromFormattedString(String formattedString) {
        String[] parts = formattedString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            Task todo = new ToDo(description);
            todo.isDone = isDone;
            return todo;
        case "D":
            String by = parts[3];
            Task deadline = new Deadline(description, by);
            deadline.isDone = isDone;
            return deadline;
        case "E":
            String from = parts[3];
            String to = parts[4];
            Task event = new Event(description, from, to);
            event.isDone = isDone;
            return event;
        default:
            // Handle unrecognized task type
            return null;
        }
    }
}

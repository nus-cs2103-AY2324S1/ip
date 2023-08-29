/**
 * Class that represents a task added by the user.
 */
public abstract class Task {
    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean that represents whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor used to create a task.
     * @param description Description of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    public abstract String toSaveFormat();
    public static Task fromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            // Invalid format
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                // Parse the saved format for Deadline (modify as needed)
                // Example: "D | 1 | Buy groceries | 2023-08-31"
                String deadlineBy = parts[3];
                return new Deadline(description, deadlineBy, isDone);
            case "E":
                // Parse the saved format for Event (modify as needed)
                // Example: "E | 0 | Team meeting | 2023-09-01 | 2023-09-02"
                String eventFrom = parts[3];
                String eventTo = parts[4];
                return new Event(description, eventFrom, eventTo, isDone);
            default:
                return null; // Unknown type
        }
    };
}

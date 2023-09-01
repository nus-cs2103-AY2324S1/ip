/**
 * Represent a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a todo task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a todo task that could be done or undone.
     *
     * @param isDone Whether the task is done or undone
     * @param description The description of the task that the user inputs
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Creates a todo task from the save format.
     *
     * @param formattedTask The string representation of the todo task
     * @return A todo task
     */
    public static ToDo createFromSaveFormat(String formattedTask) {
        String[] args = formattedTask.split(" \\| ");
        boolean isDone = args[1].equals("1");
        return new ToDo(isDone, args[2]);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}

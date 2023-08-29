package pooh;

/**
 * Abstract class representing a task.
 * <p>
 * This class serves as the basis for different types of tasks and encapsulates
 * the basic information and operations related to a task.
 * </p>
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Checks whether the task is done or not.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Retrieves the status icon based on the task's completion status.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");    // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string format suitable for file storage.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String writeTaskToFile();

    /**
     * Creates a task from its string representation in a file.
     *
     * @param input A string containing the task's information.
     * @return A new Task object corresponding to the input string.
     */
    public static Task readTaskFromFile(String input) {
        String[] args = input.split(" \\| ");
        Task task;
        switch (args[0]) {
        case "T":
            task = Todo.readTaskFromFile(args);
            break;
        case "D":
            task = Deadline.readTaskFromFile(args);
            break;
        case "E":
            task = Event.readTaskFromFile(args);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyword The string keyword to search for in the task description.
     * @return true if the task description contains the keyword, false otherwise.
     */
    public boolean ifDescriptionContains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Converts the task to a user-friendly string representation.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

package crusader.task;

/**
 * A task to do in the bot.
 */
public abstract class Task {
    /** Name of the task */
    private final String name;

    /** The completion status of the task */
    private boolean isDone;

    /**
     * Initializes a task.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Gets the name of the task
     * @return Task's name
     */
    public String getName() {
        return name;
    }

    /**
     * Generaties a string representation of the task
     * @return A string describing the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone() ? "X" : " ", name);
    }

    /**
     * Generates a file representation of the task
     * @return A string optimized to store in file
     */
    public abstract String toFormat();

    /**
     * Checks wheter the task has been completed
     * @return Task completion status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as complete
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Designates a task as incomplete
     */
    public void unmark() {
        isDone = false;
    }
}

package chatbot.tasks;

/**
 * Task class that represents a task scheduled by user.
 */
public class Task {
    /**
     * Enum of different priority levels.
     */
    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }
    private String name;
    private boolean isDone;
    private Priority priority;

    /**
     * Constructor to instantiate a new Task object.
     * @param name Task description provided by user
     */
    public Task(String name) {
        this.name = name;
        this.priority = Priority.LOW;
    }

    /**
     * Constructor to instantiate a previously stored Task object.
     * @param name Task description
     * @param isDone Whether the task has been marked as done
     * @param priority The priority level of the task
     */
    public Task(String name, boolean isDone, Priority priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Obtain the String representation of the task's priority level.
     * @return A single-letter representation of the priority level
     */
    public String getPriority() {
        switch (this.priority) {
        case HIGH:
            return "H";
        case MEDIUM:
            return "M";
        default:
            return "L";
        }
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.checkIsDone() ? "X" : " ",
                this.getName());
    }
}

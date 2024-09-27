package ekud.tasks;
/**
 * Represents a task created by the user.
 */
public abstract class Task {
    // Every task has a description
    protected String description;
    // Marks whether the task is completed already or not
    protected boolean isDone;
    // Indicates the priority level of an item
    private Priority priority;

    public Task(String description, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns a checked symbol if this task is done.
     * @return char
     */
    public char getDoneSymbol() {
        return this.isDone ? 'X': ' ';
    }

    /**
     * Returns the string representation of this task's priority.
     * @return String
     */
    public String getPriority() {
        switch(this.priority) {
        case HIGH:
            return "high";
        case MEDIUM:
            return "medium";
        case LOW:
            return "low";
        default:
            return "undefined";
        }
    }

    /**
     * Returns a compact format for saving this Task to the hard disk,
     * which is then easily parsed for future loading of saved tasks.
     * @return String
     */
    abstract public String getSaveFormat();
}

package taskmate.tools.tasks;

/**
 * The Task class is an abstract class that represents a task specified by the user. It can be of type `Todo`,
 * `Deadline`, or `Event`.
 */
public abstract class Task {

    String name;
    boolean isDone;

    /**
     * Task constructor that allows the developer to specify the name of the task.
     *
     * @param name the name of the task.
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Task constructor that allows the developer to specify the name of the task and whether the task has been
     * completed.
     *
     * @param name the name of the task.
     * @param isDone a boolean variable that represents if the task has been completed.
     */
    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the isDone instance attribute to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone instance attribute to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the isDone instance attribute.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return a String object representing the name of the Task instance.
     */
    public String getTaskName() {
        return this.name;
    }

    /**
     * Gets the type of the task. It can be of type `Todo`, `Deadline`, or `Event`.
     */
    abstract String getTaskType();

    /**
     * Formats the Task instance in a way that is written to the disk. The format is as follows:
     *
     * `[A][B] <name> ...`
     * A: A character representing the task type ('T' for 'Todo', 'D' for 'Deadline', 'E' for 'Event').
     * B: A character representing if the task has been completed. 'X' for completed, ' ' for incomplete.
     * <name>: The name of the task.
     * ...: Other information about the task such as the "by" clause (for 'Deadline' type tasks), and "from" & "to"
     * clauses (for 'Event' type tasks).
     *
     * Example 1:
     * [T][ ] Read book
     * Example 2:
     * [D][X] Complete CS2103 Assignment (by: 2023-01-01)
     * Example 3:
     * [E][ ] Attend career fair (from: 2023-01-02 to: 2023-01-03)
     *
     * @return a String that formats the information about the Task instance to be written to the disk. The type of
     * information is explained above.
     */
    public abstract String formatTaskForSaving();
    // String format to save the task to disk

}

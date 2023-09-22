package duke.tasks;

/**
 * The Task class represents a basic task with a name and completion status.
 * It provides methods to work with tasks, such as marking them as done and retrieving their details.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String note;

    /**
     * Constructs a Task with the given task name and sets its completion status to false.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.note = null;
    }

    /**
     * Constructs a Task with the given task name and completion status.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void addNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }
    /**
     * Constructs a default Task with an untitled task name and sets its completion status to false.
     * This constructor is used when no task name is provided.
     */
    public Task() {
        this.taskName = "Untitled task";
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task by setting its completion status to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Overrides the toString() method to provide a custom string representation of the task.
     *
     * @return A formatted string containing task status and name.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "[X]" : "[ ]";
        return status + " " + taskName;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}

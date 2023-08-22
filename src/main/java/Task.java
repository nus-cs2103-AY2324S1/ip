/**
 *  Represents a generic task within the chat bot application.
 */
public abstract class Task {

    /**
     * Three different task types
     */
    protected enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * The Task Type of the Task.
     */
    protected Task.TaskType TaskType;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected Boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) throws DukeException{
        if (description.trim().length() == 0) {
            throw new DukeException("The description cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task including the completion status
     * and the description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description ;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }
}

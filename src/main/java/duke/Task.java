package duke;

public class Task {
    protected String description;
    protected boolean isDone;

<<<<<<< HEAD
    public Task (String description) {
=======
    /**
     * Constructs a Task object.
     *
     * @param description  The description of the task.
     */
    public Task(String description) {
>>>>>>> branch-A-JavaDoc
        this.description = description;
        this.isDone = false;
    }

<<<<<<< HEAD
=======
    /**
     * Marks the task as done.
     */
>>>>>>> branch-A-JavaDoc
    public void markAsDone() {
        this.isDone = true;
    }

<<<<<<< HEAD
=======
    /**
     * Marks the tasks as not dome yet.
     */
>>>>>>> branch-A-JavaDoc
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's description for data storage.
     *
     * @return  The task's description.
     */
    public String toDataString() {
        return description;
    }

<<<<<<< HEAD
=======
    /**
     * Returns a string representation of the task.
     *
     * @return  A string representation of the task.
     */
>>>>>>> branch-A-JavaDoc
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}

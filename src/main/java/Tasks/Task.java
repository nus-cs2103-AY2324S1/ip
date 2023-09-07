package Tasks;

public abstract class Task {

    /** The name of the task */
    private String name;

    /** The completion status of the task */
    private boolean isDone;

    /**
     * Initializes the name and completion status of a task.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }
    public String getName() {
        return this.name;
    }
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a status icon to help with the string representation of a task.
     *
     * @return The string "[X]" if a task is done and "[]" otherwise.
     */
    public String getStatusIcon() {
        return this.isDone() ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getName());
    }

    /**
     * Returns a string representation of the task, for the purpose of recording in a storage file.
     *
     * @param isWritten The boolean paramter telling us that we want the string representation to be in the storage format.
     * @return The storage format of a string representation of a task.
     */
    public String toString(boolean isWritten) {
        return String.format("TK %s", this.getName());
    }
}
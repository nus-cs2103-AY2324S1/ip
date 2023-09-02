package duck.task;

import duck.DuckException;

/**
 * Represents a task. Abstract class that cannot be instantiated.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates a task with the given name and isDone status.
     * 
     * @param name   Name of the task.
     * @param isDone Status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     * 
     * @throws DuckException If the task is marked.
     */
    public void mark() throws DuckException {
        if (this.isDone == true) {
            throw new DuckException("Error - Task is already marked.");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Unmarks the task.
     * 
     * @throws DuckException If the task is not marked.
     */
    public void unmark() throws DuckException {
        if (this.isDone == false) {
            throw new DuckException("Error - Task is already unmarked.");
        } else {
            this.isDone = false;
        }
    }

    /**
     * Converts the task into its string representation for saving to file.
     * Should be overidden by subclasses to include type and additional fields.
     * 
     * @return Savable string representation of the task.
     */
    public String stringify() {
        String done = String.valueOf(this.isDone ? '1' : '0');
        String nameLength = String.valueOf(this.name.length()) + "/";
        return done + nameLength + this.name;
    }

    /**
     * Converts the task into a readable string representation for printing.
     * Should be overidden by subclasses to include type and additional fields.
     * 
     * @return Readable string representation of the task.
     */
    @Override
    public String toString() {
        char done = this.isDone ? 'X' : ' ';
        return "[" + done + "] " + name;
    }

    /**
     * Checks equality with another object, based on name and isDone status.
     * Should be overidden by subclasses to check additional fields.
     * 
     * @param other Object to compare to.
     * @return True if the object is a Task with the same name and status, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.name.equals(otherTask.name) && (this.isDone == otherTask.isDone);
        }
        return false;
    }
}

package duke.task;

/**
 * Represents a Task object with a name and a toggleable status
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a new Task
     * @param name Name of the task
     * @param isDone Status of whether the task is done
     */
    public Task(String name, String isDone) {
        this.name = name;
        this.isDone = isDone.equals("1");
    }

    /**
     * Sets the isDone status to true
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the isDone status to false
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task isDone status to be printed
     * @return "[X]" if done, "[ ]" if not done
     */
    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of the task isDone status to be saved
     * @return "1" if done, "0" if not done
     */
    public String getDataStatus() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Returns the string representation of the task and task status to be saved
     * @return "1" if done, "0" if not done + "|" + name of the task
     */
    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns the string representation of the task and task status to be printed
     * @return "[X]" if done, "[ ]" if not done + name of the task
     */
    @Override
    public String toString() {
        String str = this.getStatus() + " " + this.name;
        return str;
    }
}

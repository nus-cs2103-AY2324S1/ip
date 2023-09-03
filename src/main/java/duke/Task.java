package duke;

/**
 * Represents a tasks in the task list.
 */
public class Task {

    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if task is done
     * @return X if task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Generates the string to represent the task in storage
     * @return string that represent the task
     */
    public String toFileString() {
        if(isDone) {
            return "1--" + this.description;
        } else {
            return "0--" + this.description;
        }
    }
}


package pardiyem.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Whoops, a task needs to have a non-empty description!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) throws IllegalArgumentException {
        this(description, false);
    }

    /**
     * Utility function to convert the "done-ness" of a task to a string
     *
     * @return "X" if the isDone attribute is true, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Utility function to mark a task as done and return a status message
     *
     * @return status message corresponding to whether the isDone attribute is already true prior to the function-call
     */
    public String markAsDone() {
        if (this.isDone) {
            return "Hey, just letting you know that the task has already been done previously :)";
        } else {
            this.isDone = true;
            return "Bellisimo! I've marked this task as done!";
        }
    }

    /**
     * Utility function to mark a task as undone and return a status message
     *
     * @return status message corresponding to whether the isDone attribute is already false prior to the function-call
     */
    public String markAsUndone() {
        if (!this.isDone) {
            return "Hey, just letting you know that the task has already been marked as undone previously:)";
        } else {
            this.isDone = false;
            return "Va bene, I've marked this task as undone";
        }
    }

    /**
     * Overriden comparison function to compare with other objects.
     * Comparison of 2 Task objects will be done through the value returned when
     * toString() is called on both objects
     *
     * @param obj object to be compared with
     * @return whether the two objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Task) {
            result = this.toString().equals(obj.toString());
        }
        return result;
    }
}

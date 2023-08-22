abstract class Task {
    protected String description;
    protected boolean isDone = false;

    protected Task(String description) throws DukeException {
        String trimmedDescription = description.trim();
        if (trimmedDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        this.description = trimmedDescription;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of a task.
     * @return a string representation containing whether the task is completed and what it is.
     */
    public String toString() {
        String doneString = this.isDone ? "X" : " ";
        return "[" + doneString + "] " + this.description;
    }
}

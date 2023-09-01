package Duke;

/**
 * The ToDo class represents a to-do task for the Duke program.
 * It extends the SingleTask class and provides additional methods specific to to-do tasks.
 */
public class ToDo extends SingleTask {
    public ToDo(String s) {
        super(s);
    }

    /**
     * Marks the task as done and displays a message to the user.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    /**
     * Returns the status icon of the task.
     * @return a string representing the status icon of the task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Unmarks the task as done and displays a message to the user.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "OK DONE ALR added your todo ah:\n" +
                "[T]" + "[" +this.getStatusIcon() +"] " + this.description;

    }

    /**
     * Returns a string representation of the task for displaying in a list.
     * @return a string representing the task in a list
     */
    @Override
    public String listString() {
        return ". [T]" + "[" +this.getStatusIcon() +"] " + this.description;
    }

    /**
     * Returns a string representation of the task when it is removed from a list.
     * @return a string representing the task when it is removed
     */
    @Override
    public String remove() {
        return "OK DONE ALR removed your todo ah:\n" +
                "[T]" + "[" +this.getStatusIcon() +"] " + this.description;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * @return a string representing the task in a save file
     */
    @Override
    public String toSaveString() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + description;
    }
}

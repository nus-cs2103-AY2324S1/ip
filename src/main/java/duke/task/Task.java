package duke.task;
public abstract class Task {
    private String description;

    private boolean isDone;

    /**
     * Constructor.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return The icon which represent whether this task is done.
     */
    public String getStatusIcon() {
        return isDone ? "X": " ";
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks this task, mark this task as undone.
     */
    public void unmark() {
        this.isDone = false;

    }

    /**
     * Returns the string with saving format of this task.
     * @return String with savin format of this task.
     */
    public String saveString() {
        return isDone ? "1/" + description : "0/" + description;
    }

    /**
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean isContains(String keyword) {
        String[] arr = description.split(" ");
        for (String str: arr) {
            if (str.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

}

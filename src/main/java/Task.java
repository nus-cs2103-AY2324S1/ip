public class Task {
    /** task name description */
    protected String description;
    /** indicate if task is done */
    protected boolean isDone;

    /**
     * Initialize Task object with task name and task is not done.
     *
     * @param description type String;
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return status icon of the task whether it is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString(){
        return ("[" + getStatusIcon() + "] " + this.description);
    }

    /**
     * Output String format to be written in file.
     * Format : Task description|isDone
     *
     * @return formatted String
     */
    public String fileFormat() {
        String kk;
        if (this.isDone) {
            kk = "1";
        } else {
            kk = "0";
        }
        return this.description + "|" + kk;
    }
}

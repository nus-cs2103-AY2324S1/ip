package tasket.task;

/**
 * The class for task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String[] tags;

    /**
     * Constructs for task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tags = null;
        this.isDone = false;
    }

    /**
     * Constructs for task.
     * This version includes tags.
     *
     * @param description The description of the task.
     * @param tags The tags of the task.
     */
    public Task(String description, String[] tags) {
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    /**
     * Returns the task status in string format.
     *
     * @return X if it's done, otherwise a black space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task status in save format.
     *
     * @return 1 if it's done, otherwise 0.
     */
    public String getSaveStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task to undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the task in string format.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns the task in save format.
     *
     * @return The save format of the task.
     */
    public String toSaveString() {
        return String.format("%s | %s", getSaveStatusIcon(), this.description);
    }

    /**
     * Returns the tags for this task.
     * This is suitable for both GUI and save file.
     *
     * @return The tags.
     */
    protected String getTags() {
        StringBuilder sb = new StringBuilder();

        if (this.tags == null) {
            return sb.toString();
        }

        for (String tag : this.tags) {
            sb.append("#").append(tag).append(" ");
        }

        return sb.toString().trim();
    }
}

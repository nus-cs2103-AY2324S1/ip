package geraldbot.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void toggleCompletion() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String fileFormat() {
        String output = "| " + (isDone ? "1" : "0") + " | " + this.description;
        return output;
    }

    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}
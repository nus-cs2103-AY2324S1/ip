package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n" +
                "%s\n" + "----------\n", this);
    }
    public String unMarkDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n" +
                "%s\n" + "----------\n", this);
    }
    public boolean match(String keyword) {
        return description.contains(keyword);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

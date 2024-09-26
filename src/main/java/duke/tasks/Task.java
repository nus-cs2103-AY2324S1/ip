package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String res = String.format("[%s] %s", getStatusIcon(), this.description);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;
        return t.description.equals(this.description);
    }


    public abstract String makeFormat();

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


}

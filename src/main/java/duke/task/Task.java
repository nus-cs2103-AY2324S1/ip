public class Task {
    protected String description;
    protected boolean isDone;
    private String horizontal = "____________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getNumber() {
        return (isDone ? "1" : "0"); // mark done task with X
    }
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    };

    public void unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException(horizontal + "\nOOPS!!! You did not mark this task.\n" + horizontal);
        }
        this.isDone = false;
    };

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +  "] " + this.description;
    }
}
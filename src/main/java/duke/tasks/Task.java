package duke.tasks;


public class Task {
    protected String taskName;
    protected boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    //default constructor
    public Task() {
        this.taskName = "Untitled task";
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String status = this.done ? "[X]" : "[ ]";
        return status + " " + taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.done;
    }

}

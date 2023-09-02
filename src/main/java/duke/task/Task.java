package duke.task;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, String isDone) {
        this.name = name;
        this.isDone = isDone.equals("1");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String getDataStatus() {
        return this.isDone ? "1" : "0";
    }

    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        String str = this.getStatus() + " " + this.name;
        return str;
    }
}

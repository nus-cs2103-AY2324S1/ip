package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toText() {
        return "D " + this.getDoneStatus() + " " + this.description + " /" + this.end;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (" + this.end + ")";
    }
}

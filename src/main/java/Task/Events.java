package Task;

import Task.Task;

public class Events extends Task {
    protected String from;
    protected String to;
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Events(boolean status, String description, String from, String to) {
        super(status, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String dataFormat() {
        return "event/" +
                super.isDone.toString() + "/" +
                super.description + "/" +
                this.from + "/" +
                this.to;
    }
}

package duke.task;

public class ToDo extends Task {
    protected String by;
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toWriteString() {
        return "T | " + super.toWriteString();
    }
}
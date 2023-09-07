package duke;

public class ToDo extends Task {

    protected boolean isDone = false;

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        String divider = " | ";
        return "T" + divider + super.toSaveString();
    }
}
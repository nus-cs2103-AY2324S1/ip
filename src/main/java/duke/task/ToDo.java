package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        String type = "T";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
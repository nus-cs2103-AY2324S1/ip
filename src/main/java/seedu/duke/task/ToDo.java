package seedu.duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String type() {
        return "[T]";
    }

    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return type() + super.toString();
    }
}

package seedu.duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String type() {
        return "[T]";
    }

    @Override
    public String toString() {
        return type() + super.toString();
    }

    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}

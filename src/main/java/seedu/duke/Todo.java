package seedu.duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toStringForSave() {
        return "T" + " | " + super.toStringForSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

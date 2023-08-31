package duke.task;

public class Todo extends Task {
    public Todo(String description) {
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
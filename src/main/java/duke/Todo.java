package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

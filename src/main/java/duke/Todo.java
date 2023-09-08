package duke;

public class Todo extends Task {

    public Todo(Duke duke, String description) {
        super(duke, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke;

public class Todo extends Task {
    private String Input;
    public Todo(String name, String input) {
        super(name, input);
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[T][ ] " + this.getName();
        } else {
            return "[T][X] " + this.getName();
        }
    }
}

package duke;

public class Todo extends Task {
    // Constructor for duke.Todo
    public Todo(String name) {
        super(name);
    }

    // Constructor for duke.Todo with done status
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    // Gets string representation of the duke.Todo
    public String toString() {
        return "[T]" + super.toString();
    }

    // Gets string representation of the duke.Todo for hard disk
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        return "T|" + isDoneField + "|" + nameField;
    }
}

package ip.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T][" + (getIsDone() ? "X" : " ") + "] " + getDescription();
    }
}

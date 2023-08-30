package duke.task;

public class Todo extends Task {
    private static final String type = "[T]";
    public Todo(String name) {
        super(name);
    }

    @Override
    public String stringifyTask() {
        return String.format("T|%d|%s", this.done ? 1 : 0, this.name);
    }
    @Override
    public String toString() {
        return type + super.toString();
    }
}

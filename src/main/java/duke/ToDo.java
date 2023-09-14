package duke;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTaskListEntry() {
        return String.format("T | %d | %s", this.getIsDone() ? 1 : 0, this.getName());
    }
}

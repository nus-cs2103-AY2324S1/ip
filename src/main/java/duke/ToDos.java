package duke;

public class ToDos extends Task {

    protected String by;

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
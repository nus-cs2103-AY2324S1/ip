package chatterbot.data;

public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Invalid input! No ttask description.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String forFile() {
        return "todo " + this.description;
    }
}
package taskclasses;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String formatToFile() {
        return "T | " + super.formatToFile();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

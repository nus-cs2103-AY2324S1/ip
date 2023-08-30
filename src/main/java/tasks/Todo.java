package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String status, String description) {
        super(description);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}

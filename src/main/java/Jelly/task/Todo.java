package Jelly.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
}

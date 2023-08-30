package task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "T | " + bool + " | " + this.description + "\n";
    }
}
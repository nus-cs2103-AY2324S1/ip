package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getInput() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }
    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.description;
    }
}

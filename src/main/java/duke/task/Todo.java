package duke.task;

public class Todo extends Task {
    public Todo(String message) {
        super(message);
    }

    public String toSaveFormatString() {
        return "T | " + this.getStatusNumber() + " | " + this.message;
    }

    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.message;
    }
}

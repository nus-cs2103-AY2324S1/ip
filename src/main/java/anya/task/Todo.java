package anya.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, Boolean isDone) {
        super(description);
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String getType() {
        return "T";
    }

    public String formatToSave() {
        return "T" + super.formatToSave();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

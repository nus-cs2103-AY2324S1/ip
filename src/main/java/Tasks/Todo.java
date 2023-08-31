package Tasks;

import Tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}

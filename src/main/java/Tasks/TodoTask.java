package Tasks;

import enums.TaskType;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String toString() {
        return TaskType.TODO.toSymbol() + super.toString();
    }
}
package Tasks;

import enums.TaskType;

public class DeadlineTask extends Task {

    protected String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return String.format(" (by: %s)", this.deadline);
    }

    public String toString() {
        return TaskType.DEADLINE.toSymbol() + super.toString() + this.getDeadline();
    }
}
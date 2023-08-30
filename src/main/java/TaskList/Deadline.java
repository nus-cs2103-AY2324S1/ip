package TaskList;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(Boolean isDone, String name, String deadline) {
        super(isDone, name);
        this.deadline = deadline;
    }

    public String toText() {
        return "Deadline - "  + isDone + " - " + name + " -" + deadline + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}

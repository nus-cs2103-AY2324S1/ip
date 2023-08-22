package task;

public class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}

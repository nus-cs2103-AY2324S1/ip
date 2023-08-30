package ekud.state;

import ekud.util.DateTime;

public final class DeadlineTask extends Task {
    private final DateTime by;

    public DeadlineTask(String title, boolean isDone, DateTime by) {
        super(title, isDone);
        this.by = by;
    }

    public DateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}

package state;

import java.time.LocalDateTime;

public final class DeadlineTask extends Task {
    private final LocalDateTime by;

    public DeadlineTask(String title, boolean isDone, LocalDateTime by) {
        super(title, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public DeadlineTask mark() {
        return new DeadlineTask(getTitle(), true, by);
    }

    @Override
    public DeadlineTask unmark() {
        return new DeadlineTask(getTitle(), false, by);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}

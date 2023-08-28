package state;

public final class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String title, boolean isDone, String by) {
        super(title, isDone);
        this.by = by;
    }

    public String getBy() {
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

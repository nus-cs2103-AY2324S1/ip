package action;

import util.DateTime;

public final class CreateDeadlineAction extends Action {
    private final String title;
    private final DateTime by;

    public CreateDeadlineAction(String title, DateTime by) {
        this.title = title;
        this.by = by;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("deadline %s /by %s", title, by);
    }
}

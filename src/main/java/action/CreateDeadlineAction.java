package action;

import java.time.LocalDateTime;

import util.DateTime;

public final class CreateDeadlineAction extends Action {
    private final String title;
    private final LocalDateTime by;

    public CreateDeadlineAction(String title, LocalDateTime by) {
        this.title = title;
        this.by = by;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("deadline %s /by %s", title, by.format(DateTime.FORMATTER));
    }
}

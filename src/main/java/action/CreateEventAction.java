package action;

import java.time.LocalDateTime;

import util.DateTime;

public final class CreateEventAction extends Action {
    private final String title;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public CreateEventAction(String title, LocalDateTime from, LocalDateTime to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("event %s /from %s /to %s", title, from.format(DateTime.FORMATTER),
                to.format(DateTime.FORMATTER));
    }
}

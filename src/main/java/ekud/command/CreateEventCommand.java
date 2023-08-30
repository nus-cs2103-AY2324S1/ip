package ekud.command;

import ekud.util.DateTime;

public final class CreateEventCommand extends Command {
    private final String title;
    private final DateTime from;
    private final DateTime to;

    public CreateEventCommand(String title, DateTime from, DateTime to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getFrom() {
        return from;
    }

    public DateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("event %s /from %s /to %s", title, from, to);
    }
}

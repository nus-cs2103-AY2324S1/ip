package ekud.command;

import ekud.util.DateTime;

public final class CreateDeadlineCommand extends Command {
    private final String title;
    private final DateTime by;

    public CreateDeadlineCommand(String title, DateTime by) {
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

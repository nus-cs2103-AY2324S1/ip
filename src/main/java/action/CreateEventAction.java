package action;

public final class CreateEventAction extends Action {
    private final String title;
    private final String from;
    private final String to;

    public CreateEventAction(String title, String from, String to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("event %s /from %s /to %s", title, from.format(DateTime.FORMATTER),
                to.format(DateTime.FORMATTER));
    }
}

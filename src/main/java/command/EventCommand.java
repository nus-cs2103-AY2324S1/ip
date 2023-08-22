package command;

public final class EventCommand extends TaskCommand {
    private String from;
    private String to;

    EventCommand(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

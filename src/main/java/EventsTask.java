public class EventsTask extends Task {
    private final String from;
    private final String to;
    public EventsTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String typeIcon = "[E]";
        return String.format("%s%s (from: %s to: %s)",
                typeIcon, super.toString(), this.from, this.to);
    }
}

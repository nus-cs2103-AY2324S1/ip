public class Event extends Task {
    private static String SYMBOL = "E";
    protected String startDatetime;
    protected String endDatetime;

    public Event(String description, String startDatetime, String endDatetime) {
        super(description);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    public String getDataString() {
        return String.join(" | ", Event.SYMBOL, super.isDone ? "1" : "0", super.getDescription(), this.startDatetime, this.endDatetime);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", Event.SYMBOL, super.toString(), this.startDatetime, this.endDatetime);
    }
}

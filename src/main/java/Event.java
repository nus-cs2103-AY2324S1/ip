public class Event extends Task {
    protected String starting;
    protected String ending;
    public Event(String description) {
        super(description.substring(0, description.indexOf("/from") - 1));
        int fromIndex = description.indexOf("/from");
        String tempStarting = description.substring(fromIndex + 6);
        int toIndex = tempStarting.indexOf("/to") + fromIndex + 6;
        this.starting = tempStarting.substring(0, toIndex - fromIndex - 7);
        this.ending = description.substring(toIndex + 4);

    }
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description + " (from: " + starting + " to: " + ending + ")";
    }
}

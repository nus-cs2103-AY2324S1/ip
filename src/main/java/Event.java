public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description, "E");
        this.start = start;
        this.end = end;
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return String.format(" (from: %s to: %s)", start, end);
    }
}

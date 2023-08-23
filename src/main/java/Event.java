public class Event extends Task{
    private String from;
    private String to;

    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[E]" + mark + title + " (from: " + this.from + " to: " + this.to + ")";
    }
}

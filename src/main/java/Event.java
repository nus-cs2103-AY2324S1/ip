public class Event extends Task {

    protected String taskDescription;
    protected String from;
    protected String to;
    private String identifier;


    public Event(String taskDescription, String from, String to, boolean isDone) {
        super(taskDescription, isDone);
        this.from = from;
        this.to = to;
        this.identifier = "[E]";
    }

    public String toString() {
        return this.identifier + super.toString() + " (from: " + from +  " to: " + to + ")";
    }
}

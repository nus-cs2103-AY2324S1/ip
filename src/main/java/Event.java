public class Event extends Task{
    protected String from;
    protected String to;
    public Event (String description, String from, String to) {
        //no extra information for todolist
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String first = "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(from: " + this.from + " to: " + this.to + ")";
        return first + second;
    }
}

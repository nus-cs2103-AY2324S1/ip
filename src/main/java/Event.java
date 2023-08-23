public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from , String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + super.size + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
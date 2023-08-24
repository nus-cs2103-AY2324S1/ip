public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String displayTask() {
        return (".[E]" + super.getStatusIcon() + description +
                "(from: " + from + "to: " + to + ")");
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E]" + super.getStatusIcon() + description +
                "(from: " + from + "to: " + to + ")");
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[E]" + super.getStatusIcon() + description +
                " (from: " + from + "to: " + to + ")");
    }
}

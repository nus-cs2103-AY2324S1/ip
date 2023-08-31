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

    public static Event readFromFile(String[] segments) {
        String symbol = segments[1];
        String description = segments[2];
        String from = segments[3];
        String to = segments[4];
        Event toReturn =  new Event(description, from, to);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
    }

    @Override
    public String toWriteString() {
        String toWrite = "E" + " | " + super.toWriteString() + " | " + from + " | " + to;
        return toWrite;
    }
}

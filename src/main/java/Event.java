public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static Event readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Event event = new Event(components[2], components[3], components[4]);
        if(isDone) {
            event.markDone();
        }
        return event;
    }
    @Override
    public String writeFileFormat() {
        //store as E|1/0|this.start|this.end
        return "E|" + super.writeFileFormat() + "|" + this.start + "|" + this.end;
    }
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + "(from: "
                + this.start
                + " to: "
                + this.end
                + ")";
    }
}

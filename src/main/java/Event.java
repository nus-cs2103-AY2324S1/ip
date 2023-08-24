public class Event extends Task{
    private String start;
    private String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        this.type = "E";
    }
    @Override
    public String toString() {
        return super.toString() + "(from: " + start + " to: " + end +")";
    }
}

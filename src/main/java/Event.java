public class Event extends Task {

    protected String start;

    protected String end;

    public Event(String des, String start, String end) {
        super(des);
        this.start = start;
        this.end = end;
    }

    public Event(String des, String start, String end, boolean mark) {
        super(des, mark);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end +  ")";
    }


}

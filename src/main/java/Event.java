public class Event extends Task{
    String start;
    String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public String display() {
        if(done) {
            return "[E][X] " + this.name + " (From: " + start + " To: " + end + ")";
        }
        return "[E][] " + this.name + " (From: " + start + " To: " + end + ")";
    }
}
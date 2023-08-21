//this class represents an event
public class Event extends Task{
    String start;
    String end;
    //default constructor to store start and end
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    //default display to represent the event object
    public String display() {
        if(done) {
            return "[E][X] " + this.name + " (From: " + start + " To: " + end + ")";
        }
        return "[E][] " + this.name + " (From: " + start + " To: " + end + ")";
    }
}
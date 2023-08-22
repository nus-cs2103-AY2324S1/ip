package tasks;

public class Event extends Task{
    private String from;
    private String to;
    
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

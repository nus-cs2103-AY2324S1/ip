/*
 * Event class that inherits from Task.
 * 
 * @var from Representing start time
 * @var to Representing end time
 * 
 * @author Owen Yeo
 */
public class Event extends Task{
    private String from;
    private String to;

    Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}

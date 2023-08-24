public class Event extends Task {

    protected String startTime;
    protected String endTime;
    
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to " + endTime + ")";
    }
}

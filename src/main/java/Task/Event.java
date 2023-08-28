package Task;

public class Event extends Task {

    private static final String TASK_HEADER = "[E] ";
    private final String startTime;
    private final String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return Event.TASK_HEADER + super.toString()+
                " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}

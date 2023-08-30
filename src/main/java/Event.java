public class Event extends Task{

    protected String startDate;
    protected String endDate;
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return " [E]" + (super.isDone ? "[X] " : "[ ] ") +  super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}

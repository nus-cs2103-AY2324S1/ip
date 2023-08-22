public class Event extends Task {

    private String from;
    private String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    private String getDuration() {
        return from + " - " + to;
    }

    @Override
    public String getTask() {
        return "Event ->" + super.getTask() + "(" + getDuration() + ")";
    }
}

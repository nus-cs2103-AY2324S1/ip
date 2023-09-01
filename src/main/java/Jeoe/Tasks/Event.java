package Jeoe.Tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String getStartDateTimeString() {
        return this.from;
    }

    public String getEndDateTimeString() {
        return this.to;
    }

    @Override
    public String replyString(int currNumOfTask) {
        return super.replyString(currNumOfTask);
    }
}

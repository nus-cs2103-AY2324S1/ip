package Tasks;

import enums.TaskType;

public class EventTask extends Task {

    protected String startDatetime;
    protected String endDatetime;

    public EventTask(String description, String start, String end) {
        super(description);
        this.startDatetime = start;
        this.endDatetime = end;
    }

    public String getEventRange() {
        return String.format(" (from: %s to %s)", this.startDatetime, this.endDatetime);
    }

    public String toString() {
        return TaskType.EVENT.toSymbol() + super.toString() + this.getEventRange();
    }
}
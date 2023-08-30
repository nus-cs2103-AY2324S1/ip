package task;

import task.Task;

public class Event extends Task {
    private String startDateTime;
    private String endDateTime;

    public Event(String description, String startDateTime,String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }

    public String getStartDateTime() {
        return startDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toFileString() {
        String type = "E";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " +
                getDescription() + " | " + startDateTime + " to " + endDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), startDateTime, endDateTime );
    }


}

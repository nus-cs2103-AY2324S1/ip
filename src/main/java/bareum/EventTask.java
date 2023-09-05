package bareum;

public class EventTask extends Task {
    private String startDateTime;
    private String endDateTime;


    private EventTask(boolean isDone, String description, String startDateTime,
                     String endDateTime) {
        super(isDone, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        // add exceptions for time
    }

    public static EventTask makeEvent(String description, String startDateTime,
                                      String endDateTime) {
        return new EventTask(false, description, startDateTime, endDateTime);
    }

    public static EventTask makeEvent(String[] taskInputs) {
        boolean isDone = taskInputs[1].equals("1");
        String description = taskInputs[2];
        String startDateTime = taskInputs[3];
        String endDateTime = taskInputs[4];
        return new EventTask(isDone, description, startDateTime, endDateTime);
    }

    public String getStartDateTime() {
        return this.startDateTime;
    }

    public String getEndDateTime() {
        return this.endDateTime;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + "(from:" + startDateTime + "to:" + endDateTime + ")";
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "E|" + done + "|" + this.description + "|" + this.startDateTime + "|" + this.endDateTime +"\n";
    }
}

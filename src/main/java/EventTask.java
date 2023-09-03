public class EventTask extends Task {
    String startDateTime;
    String endDateTime;

    private EventTask(boolean isDone, String description, String startDateTime,
                     String endDateTime) {
        super(isDone, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        // add exceptions for time
    }

    public static EventTask makeEvent(String allDetails) throws BareumException {
        if (allDetails.length() == 0) {
            throw new BareumException("Event description cannot be empty.");
        }

        String[] descriptionStartEndTime = allDetails.split("/from");
        if (descriptionStartEndTime.length <= 1) {
            throw new BareumException("Event start time cannot be empty.");
        }

        String description = descriptionStartEndTime[0];
        String[] startEndTime = descriptionStartEndTime[1].split("/to");
        if (startEndTime.length <= 1) {
            throw new BareumException("Event end time cannot be empty.");
        }

        String startDateTime = startEndTime[0];
        String endDateTime = startEndTime[1];
        return new EventTask(false, description, startDateTime, endDateTime);
    }

    public static EventTask makeEvent(String[] taskInputs) {
        boolean isDone = taskInputs[1].equals("1");
        String description = taskInputs[2];
        String startDateTime = taskInputs[3];
        String endDateTime = taskInputs[4];
        return new EventTask(isDone, description, startDateTime, endDateTime);
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

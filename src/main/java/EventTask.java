public class EventTask extends Task {
    String startDateTime;
    String endDateTime;
    public EventTask(String details) throws IllegalArgumentException {
        super(details.split("/from ")[0]);
        if (description.length() == 0) {
            throw new IllegalArgumentException("Deadline description cannot be empty.");
        }

        String[] startTimeSplit = details.split("/from ")[1].split("/to");
        String startDateTime = startTimeSplit[0];
        String endDateTime = startTimeSplit[1];
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        // add exceptions for time
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "]" + this.description
                + "(from: " + startDateTime + "to:" + endDateTime + ")";
    }
}

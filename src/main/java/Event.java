public class Event extends Task {

    private String startTime;
    private String endTime;

    /**
     * Constructs an Event with the specified name, start time, and end time.
     *
     * @param name      The name of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    private Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Parses the command string to create an Event instance.
     *
     * @param input The command string.
     * @return A new Event instance.
     * @throws DukeException If the input format is invalid.
     */
    public static Event createFromCommandString(String input) throws DukeException {
        String[] splitByTo = input.split("/to ", 2);
        if (splitByTo.length < 2) {
            throw new DukeException("Missing '/to' or end date for event.");
        }
        String[] splitByFrom = splitByTo[0].split("/from ", 2);
        if (splitByFrom.length < 2) {
            throw new DukeException("Missing '/from' or start date for event.");
        }
        return new Event(splitByFrom[0], splitByFrom[1], splitByTo[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.startTime + "to: " + this.endTime + ")";
    }
}

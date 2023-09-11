package duke;
/**
 * Event class that is a Task with fields startTime, endTime and a symbol
 */
public class Event extends Task {
    private static final String SYMBOL = "[E]";
    private String startTime;
    private String endTime;

    /**
     * Constructor for Event task
     * @param name name of the Event task
     * @param startTime start time of the event
     * @param endTime end time of the event
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * To get details of start time & end time of the event
     * @return String that contains the start and end time in a specific format
     */
    public String getDetails() {
        return "(from: " + this.startTime + " to: " + this.endTime + ")";
    }
    @Override
    public String toString() {
        return Event.SYMBOL + this.getCheckbox() + this.getName() + " " + this.getDetails();
    }
    @Override
    public String newFormat() {
        return Event.SYMBOL + " | " + this.getInt() + " | " + this.getName() + " | " + this.getDetails();
    }
    @Override
    public void update(String details) throws InvalidInputException {
        String[] eventDetails = details.split(" /from ");
        String[] timings;
        try {
            timings = eventDetails[1].split(" /to ");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Please provide valid timings");
        }
        String newName;
        String newStartTime;
        String newEndTime;
        try {
            newName = eventDetails[0];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Please update with a new name");
        }
        try {
            newStartTime = timings[0];
            newEndTime = timings[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Please provide the timings to update with");
        }
        this.setName(newName);
        this.startTime = newStartTime;
        this.endTime = newEndTime;
    }
}

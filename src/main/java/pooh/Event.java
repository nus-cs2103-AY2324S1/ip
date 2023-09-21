package pooh;

/**
 * Represents an event task with a start time and an end time.
 * <p>
 * The Event class is a subclass of the Task class and it adds two additional attributes,
 * namely eventStartTime and eventEndTime, to represent the duration of the event.
 * </p>
 */
public class Event extends Task {
    private final String eventStartTime;
    private final String eventEndTime;

    /**
     * Constructs a new Event object.
     *
     * @param description    The description of the event.
     * @param eventStartTime The start time of the event.
     * @param eventEndTime   The end time of the event.
     */
    public Event(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Serializes the Event object into a string format suitable for file storage.
     * <p>
     * This method prepares a string that captures the essential attributes of the Event object,
     * such as its type (E for Event), its completion status, its description, and its event times.
     * </p>
     *
     * @return A string containing the task's details in a format suitable for file storage.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s-%s", "E",
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.eventStartTime,
                this.eventEndTime);
    }

    /**
     * Reads task details from a given file and creates a new Event object.
     * <p>
     * This static method takes an array of strings as its argument, which should contain the task details,
     * and returns an Event object based on those details.
     * </p>
     *
     * @param args An array of strings containing the task details.
     * @return An Event object initialized with the given task details.
     */
    public static Event readTaskFromFile(String[] args) {
        String[] eventTime = args[3].split("-");
        Event newEventTask = new Event(args[2], eventTime[0], eventTime[1]);
        if (args[1].equals("1")) {
            newEventTask.markAsDone();
        }
        return newEventTask;
    }

    /**
     * Converts the Event object to a string representation.
     * <p>
     * This method overrides the toString method to provide a string representation of the Event object,
     * which includes its status (done or not), description, and event times.
     * </p>
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.eventStartTime, this.eventEndTime);
    }
}

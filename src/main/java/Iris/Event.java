package iris;
/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructor for the Event class.
     *
     * @param name      The name of the event task.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get the start time of the event.
     *
     * @return The start time.
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * Get the end time of the event.
     *
     * @return The end time.
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * Generate a string representation of the event task for writing to a file.
     *
     * @return The formatted string for file output.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s-%s", "E",
                this.isDone() ? 1 : 0,
                this.getName(),
                this.getStartTime(),
                this.getEndTime());
    }

    /**
     * Create an Event task object from an array of strings read from a file.
     *
     * @param args The array of strings containing task information.
     * @return The Event task object.
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
     * Override the toString method to provide a custom string representation of the Event task.
     *
     * @return The formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startTime, this.endTime);
    }
}


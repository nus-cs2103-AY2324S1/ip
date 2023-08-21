public class Deadline extends Task{
    /**
     * The date of the deadline.
     */
    private final String dateTime;

    /**
     * Creates a deadline with the given description and date.
     * @param description The description of the deadline.
     * @param dateTime The date and time of the deadline.
     */
    public Deadline (String description, String dateTime) {
        super.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the deadline.
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dateTime + ")";
    }
}

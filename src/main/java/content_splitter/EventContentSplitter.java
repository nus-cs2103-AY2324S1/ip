package content_splitter;

/**
 * Class that handles the splitting of content for a specific event task.
 */
public class EventContentSplitter {
    private String description;
    private String dateOfFrom;
    private String dateOfTo;

    /**
     * The constructor of this class.
     * @param content
     */
    public EventContentSplitter(String content) {
        String[] contentParts = content.split(" /from ", 2);
        String[] dateParts = contentParts[1].split(" /to ");
        description = contentParts[0];
        dateOfFrom = dateParts[0];
        dateOfTo = dateParts[1];
    }

    /**
     *  This method returns the description.
     * @return the description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  This method returns the start date of this event.
     * @return the start date.
     */
    public String getDateOfFrom() {
        return dateOfFrom;
    }

    /**
     *  This method returns the end date of this event.
     * @return the end date.
     */
    public String getDateOfTo() {
        return dateOfTo;
    }
}

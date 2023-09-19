package content_splitter;

/**
 * Class that handles the splitting of content for a specific deadline task.
 */
public class DeadlineContentSplitter {
    private String description;
    private String date;

    /**
     * The constructor of this class.
     * @param content
     */
    public DeadlineContentSplitter(String content) {
        String[] contentParts = content.split(" /by ", 2);
        description = contentParts[0];
        date = contentParts[1];
    }

    /**
     *  This method returns the description.
     * @return the description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  This method returns the date.
     * @return the date of the deadline.
     */
    public String getDate() {
        return date;
    }
}

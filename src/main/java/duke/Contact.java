package duke;

/**
 * The Contact class represents a contact.
 */
public class Contact {
    /** Description of the contact **/
    private String description;

    /**
     * Instantiates an instance of Task.
     *
     * @param description The description of the task.
     */
    public Contact(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

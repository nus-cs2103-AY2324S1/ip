package oscar.info;

/**
 * Note class that supports recording and managing textual information.
 */
public class Note extends Info {
    /**
     * Public constructor to create a note.
     *
     * @param description String description of note.
     */
    public Note(String description) {
        super(description, "N");
    }

    /**
     * Obtains string representation of note.
     *
     * @return Description of note.
     */
    @Override
    public String toString() {
        return "[" + super.type + "] " + super.description;
    }
}

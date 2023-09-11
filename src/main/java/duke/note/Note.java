package duke.note;

/**
 * Note class.
 */
public class Note {
    private String description;

    /**
     * Note Constructor.
     *
     * @param description Description of the note.
     */
    public Note(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

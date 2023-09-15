package dude;

/**
 * Represents a note.
 * A <code>Note</code> object has a <code>String</code> description.
 */
public class Note {

    private String description;

    /**
     * Constructs a new Note object with the specified description.
     *
     * @param description A short description of the note.
     */
    public Note(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts task into format to be saved in file.
     * @return format of task to be saved in file.
     */
    public String saveNote() {
        return "N | " + this.description + "\n";
    }

    public boolean containKeywords(String keywords) {
        return this.description.contains(keywords);
    }

    /**
     * Returns a formatted string representation of the note.
     *
     * @return A formatted string describing the note.
     */
    @Override
    public String toString() {
        return this.description;
    }
}

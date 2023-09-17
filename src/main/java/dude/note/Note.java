package dude.note;

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

    /**
     * Checks if note contains specified keyword.
     * @return boolean to represent if note contains the keyword.
     */
    public boolean containKeywords(String keywords) {
        String lowercaseKeywords = keywords.toLowerCase();
        String lowercaseDescription = this.description.toLowerCase();
        return lowercaseDescription.contains(lowercaseKeywords);
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

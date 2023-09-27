package rat.notes;

/**
 * This class encapsulates a note.
 * A note is a string of text that the user can add to.
 *
 * @author Keagan
 */
public class Note {

    /**
     * The body of the note.
     */
    private String body;

    /**
     * Constructor for a Note object.
     * @param input The body of the note.
     */
    public Note(String input) {
        this.body = input;
    }

    @Override
    public String toString() {
        return "[Note] " + this.body;
    }

    /**
     * Formats the note for writing to file.
     * @return The formatted note.
     */
    public String formatForFile() {
        return "N/ " + this.body;
    }

}

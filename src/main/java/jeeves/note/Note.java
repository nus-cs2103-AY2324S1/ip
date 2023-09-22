package jeeves.note;

public class Note {

    private static int noteCount = 0;
    private final String description;


    public Note(String description) {
        this.description = description;
        noteCount += 1;
    }

    /**
     * Getter method for the global Note count.
     *
     * @return Number of Notes initialized
     */
    public static int getNoteCount() {
        return noteCount;
    }

    /**
     * Decrements the number of notes.
     * Used when a note is deleted from the list
     *
     */
    public static void decrementNoteCount() {
        noteCount -= 1;
    }

    /**
     * {@inheritDoc}
     * Obtain a string representation of a Note object
     *
     * @return String representation of Note
     */
    @Override
    public String toString() {
        return String.format(description);
    }

}

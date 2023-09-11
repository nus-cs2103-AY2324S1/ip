package friday;

/**
 * Represents a note taken in the Friday application. Each note contains some content.
 */
public class Note extends Item {

    /**
     * Constructs a new Note with the specified content.
     *
     * @param content The content of the note.
     */
    public Note(String content) {
        super(content);
    }

    /**
     * Retrieves the content of the note.
     *
     * @return The content of the note.
     */
    public String getContent() {
        return this.itemName;
    }

    @Override
    public String toString() {
        return getContent();
    }
}

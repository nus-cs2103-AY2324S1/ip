package echobot.note;

/**
 * The Note class represents a simple note with a title and content.
 */
public class Note {
    private String title;
    private String content;

    /**
     * Constructs a new Note with title and content.
     *
     * @param title   The title of the note.
     * @param content The content of the note.
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Converts the Note object to a string format.
     *
     * @return A string of the note in the format "title/content".
     */
    public String toFileString() {
        return title + "/" + content;
    }

    /**
     * Creates a Note object from a string representation.
     *
     * @param fileString The string representation of the note in the format "title/content".
     * @return A new Note object created from the fileString, or null if the format is invalid.
     */
    public static Note fromFileString(String fileString) {
        String[] parts = fileString.split("/");
        if (parts.length == 2) {
            String title = parts[0];
            String content = parts[1];
            return new Note(title, content);
        } else {
            return null;
        }
    }

    /**
     * Gets the title of the note.
     *
     * @return The title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the content of the note.
     *
     * @return The content of the note.
     */
    public String getContent() {
        return content;
    }

    /**
     * Displays the note details.
     *
     * @return The note title and content.
     */
    public String display() {
        return "Title: " + getTitle() + "\n"
                + "  Content: " + getContent();
    }

}

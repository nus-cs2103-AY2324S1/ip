package jeeves.note;

public class Note {

    private final String description;

    public Note(String description) {
        this.description = description;
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

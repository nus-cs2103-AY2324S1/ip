package duke.dukeexceptions;

/**
 * An exception thrown when data is corrupt
 */
public class CorruptDataException extends Exception {
    private final String corruptLine;

    /**
     * Constructs a CorruptDataException with the given corrupt line
     *
     * @param corruptLine the line of corrupt data
     */
    public CorruptDataException(String corruptLine) {
        super("Data is corrupt");
        this.corruptLine = corruptLine;
    }

    /**
     * Returns the line where the data is corrupt
     *
     * @return the line of corrupt data
     */
    public String getCorruptLine() {
        return this.corruptLine;
    }
}

package duke.ui;

public class Ui {
    // Constants
    private static final int DEFAULT_LENGTH = 70;
    private static final int DEFAULT_SPACE = 4;
    private static final String UNDERSCORE = "_";
    private static final String SPACE = " ";
    private static final String LINE_SEPARATOR = "\n";

    // Fields
    private final int horizontalLineLength;
    private final int indentSpace;
    private StringBuilder response = new StringBuilder();

    /**
     * Default constructor uses default values for length and space.
     */
    public Ui() {
        this(DEFAULT_LENGTH, DEFAULT_SPACE);
    }

    /**
     * Constructor with custom values for length and space.
     */
    public Ui(int horizontalLineLength, int indentSpace) {
        this.horizontalLineLength = horizontalLineLength;
        this.indentSpace = indentSpace;
    }

    /**
     * Indents each line of the input with the specified amount of spaces.
     * @param input The input string.
     * @return The indented string.
     */
    private String indent(String input) {
        String delimiter = LINE_SEPARATOR + SPACE.repeat(this.indentSpace);
        String[] splitString = input.split(LINE_SEPARATOR);
        return SPACE.repeat(this.indentSpace) + String.join(delimiter, splitString);
    }

    /**
     * Displays the message between horizontal bars.
     * @param message The message to display.
     */
    public void displayMessageWithBars(String message) {
        String bar = SPACE.repeat(indentSpace) + UNDERSCORE.repeat(this.horizontalLineLength);
        System.out.println(bar + LINE_SEPARATOR + indent(message)
                + LINE_SEPARATOR + bar + LINE_SEPARATOR);
    }

    /**
     * Resets the response StringBuilder to its initial state.
     */
    public void resetResponse() {
        this.response = new StringBuilder();
    }

    /**
     * Appends a string to the current response.
     * @param response The string to append.
     */
    public void addToResponse(String response) {
        this.response.append(response);
    }

    /**
     * Retrieves the current response.
     * @return The current response as a string.
     */
    public String getResponse() {
        return this.response.toString();
    }
}

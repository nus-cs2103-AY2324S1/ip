package duke.ui;

// Solution below inspired by https://github.com/Yufannnn/ip

/**
 * The {@code Ui} class manages the user interface display functionality. It helps
 * in formatting the display content by adding indentation, horizontal bars, and managing
 * user messages. The class also provides utility functions to handle user responses.
 *
 * <p>Some of the primary functionalities include indenting lines, displaying messages
 * with horizontal bars, and managing user responses with the ability to reset, add, or retrieve them.</p>
 */
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
     * Constructor that sets the horizontal line length and indent space.
     * <p>
     * Assumes that the horizontal line length and indent space are non-negative values.
     * </p>
     *
     * @param horizontalLineLength Length of the horizontal bar.
     * @param indentSpace Space used for indentation.
     */
    public Ui(int horizontalLineLength, int indentSpace) {
        assert horizontalLineLength >= 0 : "Horizontal line length should be non-negative.";
        assert indentSpace >= 0 : "Indent space should be non-negative.";

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
     * <p>
     * Assumes the provided response string is not null.
     * </p>
     *
     * @param response The string to append.
     */
    public void addToResponse(String response) {
        assert response != null : "Response string should not be null.";
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

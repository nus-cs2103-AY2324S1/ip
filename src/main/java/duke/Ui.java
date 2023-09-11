package duke;

/**
 * The Ui class handles user interface interactions and provides methods for greetings and farewells.
 */
public class Ui {
    private static final String name = "Bartholomew Hamish Montgomery";
    static final String line = "______________________________________________________________________________________\n";

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return line + "I extend to you my utmost felicitations, User! I am " + name
                + "." + "\n" + "What may I do for you?" + "\n" + line;
    }
    /**
     * Displays a goodbye message to the user.
     */
    public String goodbye() {
        return line + "Until we meet once more in the near future, I bid you farewell." + "\n" + line;
    }

    /**
     * Returns a horizontal line used for separating sections.
     *
     * @return The horizontal line as a string.
     */
    public String line() {
        return line;
    }
}

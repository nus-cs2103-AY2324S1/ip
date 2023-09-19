package duke;

/**
 * The Ui class handles user interface interactions and provides methods for greetings and farewells.
 */
public class Ui {
    private static final String name = "Bartholomew Hamish Montgomery";
    private static final String line = "_______________________________________\n";

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return "I extend to you my utmost felicitations, User! I am " + name
                + "." + "\n" + "What may I do for you?";
    }
    /**
     * Displays a goodbye message to the user.
     */
    public String goodbye() {
        return "Until we meet once more in the near future, I bid you farewell.";
    }
    public String formatText(String text) {
        return line + text + "\n" + line;
    }
}

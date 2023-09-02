package trackerbot.utils;

import java.util.Scanner;

/**
 * Generates UI elements for TrackerBot.
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class Ui {
    /** Name of the app. **/
    private final String appName;

    /** Line separators for the console between paragraphs. **/
    private static final String FORMAT_LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /** Constant Scanner taking in the System.in stream throughout the app's lifetime */
    private static final Scanner USER_INPUT = new Scanner(System.in);

    /**
     * Constructor for the class.
     * <p>This is private, as all instances of Ui should generate the bootup message
     * on creation.</p>
     * @param appName The name of the instance.
     */
    private Ui(String appName) {
        this.appName = appName;
    }

    /**
     * Factory method for UI.
     * <p>This generates the intended side effect of a bootup message on creation.</p>
     * @param appName The name of the instance.
     * @return A new Ui instance.
     */
    public static Ui instantiate(String appName) {
        Ui ui = new Ui(appName);
        System.out.println(FORMAT_LINE);
        System.out.println("Greetings from " + ui.appName + "!");
        System.out.println("How may I assist?");
        System.out.println(FORMAT_LINE);
        return ui;
    }

    /**
     * Awaits user input in the console.
     * @return The String representation of user input.
     */
    public String readCommand() {
        String input;
        System.out.print("Format :: [keyword] [parse string] | ");
        input = USER_INPUT.nextLine();
        return input;
    }

    /**
     * Displays the separator line in the console.
     */
    public void showLine() {
        System.out.println(FORMAT_LINE);
    }

    /**
     * Displays the error message in the console.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("I got some trouble with that input...\n  " + message);
    }

    /**
     * Displays the exit message in the console.
     */
    public void exitApp() {
        System.out.println("Thank you for using " + appName + ". Goodbye.");
    }

    /**
     * Displays the status message in the console.
     * @param message The status message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}

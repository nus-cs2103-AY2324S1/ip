package trackerbot.gui;

import javafx.application.Platform;

/**
 * Generates UI elements for TrackerBot.
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class UiHandler {
    /** Name of the app. **/
    private final String appName;

    /** Stores the last known message from the app. */
    private String uiMessage;

    /**
     * Constructor for the class.
     * <p>This is private, as all instances of Ui should generate the bootup message
     * on creation.</p>
     * @param appName The name of the instance.
     */
    private UiHandler(String appName) {
        this.appName = appName;
    }

    /**
     * Factory method for UI.
     * This automatically sets the UiHandler message to be a greeting message.
     * @param appName The name of the instance.
     * @return A new Ui instance.
     */
    public static UiHandler instantiate(String appName) {
        UiHandler uiHandler = new UiHandler(appName);
        uiHandler.uiMessage = "Greetings from " + uiHandler.appName + "!\n"
                + "How may I assist?";
        return uiHandler;
    }

    /**
     * Awaits user input in the console.
     * @return The String representation of user input.
     * @deprecated UiHandler no longer works on user input.
     */
    public String readCommand() {
        String input;
        System.out.print("Format :: [keyword] [parse string] | ");
        input = "";
        return input;
    }

    /**
     * Sets the error message in UiHandler.
     *
     * @param message The error message to display.
     */
    public void setError(String message) {
        uiMessage = "Oh dear.\n" + message;
    }

    /**
     * Sets the message to be the exit message.
     */
    public void exitApp() {
        uiMessage = "Thank you for using " + appName + ". Goodbye.";
        new Thread(() -> Thread.sleep(3000))
        Platform.exit();
    }

    /**
     * Sets the message in UiHandler.
     *
     * @param message The status message to display.
     */
    public void setMessage(String message) {
        uiMessage = message;
    }

    /**
     * Gets the message in UiHandler.
     *
     * @return The String representation of the message in UiHandler.
     */
    public String getMessage() {
        return uiMessage;
    }
}

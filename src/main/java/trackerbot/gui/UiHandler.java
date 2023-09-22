package trackerbot.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Generates UI elements for TrackerBot.
 *
 * @author WZWren
 * @version Level-10
 */
public class UiHandler {
    /** Name of the app. **/
    private final String appName;

    /** Stores the last known message from the app. */
    private String uiMessage;

    /**
     * Constructs a UIHandler object for the class.
     * <p>This is private, as all instances of Ui should generate the bootup message
     * on creation.</p>
     *
     * @param appName The name of the instance.
     */
    private UiHandler(String appName) {
        this.appName = appName;
    }

    /**
     * Constructs a UIHandler object.
     * <p>Using a factory method for UIHandler allows us to run side effects during the
     * construction of the method.</p>
     * <p>This automatically sets the UiHandler message to be a greeting message.</p>
     *
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
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
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

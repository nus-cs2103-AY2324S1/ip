package carbonbot;

import java.util.ArrayList;
import java.util.List;

import carbonbot.control.MainWindow;

/**
 * Ui deals with interactions with the user such as receiving inputs and printing outputs.
 */
public class Ui {
    private final List<String> messageBuffer = new ArrayList<>();
    private final MainWindow mainWindow;

    /**
     * Constructs a new Ui to handle the displaying of output.
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays a greeting to welcome the user and ask for input.
     */
    public void showGreetings() {
        mainWindow.addCarbonDialog("Hello! I'm CarbonBot\n"
                + "What can I do for you?");;
    }

    /**
     * Displays the provided message.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        mainWindow.addCarbonDialog(message);
    }

    /**
     * Adds the message to a buffer. The message will not be displayed. Call flushBuffer() to get the buffered
     * messages.
     * @param message A string message to be addedd to buffer.
     */
    public void bufferMessage(String message) {
        messageBuffer.add(message);
    }

    /**
     * Returns the messages stored in the buffer and clears the buffer.
     * @return Messages in the buffer.
     */
    public String flushBuffer() {
        String combinedMessage = String.join("\n", messageBuffer);
        messageBuffer.clear();
        return combinedMessage;
    }

    /**
     * Shows the error message when the task list could not be loaded from disk.
     */
    public void showLoadingError() {
        this.showMessage("[!] Failed to load save file from disk. New data file will be created.");
    }
}

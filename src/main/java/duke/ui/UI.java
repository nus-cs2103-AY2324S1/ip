package duke.ui;

/**
 * UI Handler for duke application.
 */
public class UI {

    private final MainWindow mainWindow;

    private enum Colors {
        ERROR("#FF0000"),
        NORMAL("#00B0B0");

        private final String code;

        Colors(String c) {
            code = c;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    /**
     * Constructor for UI Class.
     *
     * @param mainWindow MainWindow instance to output to
     */
    public UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        sendMessage("Hello! I'm Heimdallr\nWhat can I do for you?");
    }

    /**
     * Print a message to GUI.
     *
     * @param msg The message to print
     */
    public void sendMessage(String msg) {
        mainWindow.addDialog(DialogBox.getDukeDialog(msg, mainWindow.getDukeImage(), Colors.NORMAL.code));
    }

    /**
     * Print an error to GUI.
     *
     * @param msg The error message to print
     */
    public void sendError(String msg) {
        mainWindow.addDialog(DialogBox.getDukeDialog(msg, mainWindow.getDukeImage(), Colors.ERROR.code));
    }
}

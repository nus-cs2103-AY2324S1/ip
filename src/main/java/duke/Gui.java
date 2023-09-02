package duke;

import java.util.ArrayList;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public class Gui {
    private MainWindow mainWindow;

    public Gui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        print("Hello! I'm Muddy\n" + "What can I do for you?");
    }

    /**
     * Print a message to GUI.
     *
     * @param msg The message to print
     */
    public void print(String msg) {
        mainWindow.addDialog(DialogBox.getDukeDialog(msg, mainWindow.getDukeImage()));
    }

    public void print(ArrayList<String> messages) {
        StringBuilder combinedMessage = new StringBuilder();

        for (String message : messages) {
            combinedMessage.append(message).append("\n");
        }
        mainWindow.addDialog(DialogBox.getDukeDialog(combinedMessage.toString(), mainWindow.getDukeImage()));
    }

    public void printError(String msg) {
        mainWindow.addDialog(DialogBox.getDukeDialog(msg, mainWindow.getDukeImage()));
    }

    public void showGoodbyeMessage() {
        print("\"Bye. Hope to see you again soon!\"");
    }

}

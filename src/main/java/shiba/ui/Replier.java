package shiba.ui;

import java.util.ArrayList;

import javafx.application.Platform;
import shiba.exceptions.ShibaException;
import shiba.ui.components.DialogNode;

/**
 * Represents a class that prints messages to the user.
 */
public class Replier {
    private static final ArrayList<DialogNode.SubNode> cumulativeSubNodes = new ArrayList<>();

    /**
     * Prints the greeting message.
     */
    public static void printGreeting(String botName) {
        printWithNoIndents("Woof! I'm " + botName);
        printWithNoIndents("What can I bark at you?");
        reply();
    }

    /**
     * Prints the bye message
     */
    public static void printBye() {
        printWithNoIndents("Woof! Hope to bark at you again soon!");
        reply();
    }

    /**
     * Prints the unknown command message.
     */
    public static void printUnknownCommand() {
        printWithNoIndents("Woof! I don't know what that command is!");
        reply();
    }

    /**
     * Prints the invalid command message.
     *
     * @param e The ShibaException whose message is to be printed.
     */
    public static void printException(ShibaException e) {
        printWithNoIndents("Woof! " + e.getMessage());
        reply();
    }

    /**
     * Prints the given message with no indents.
     *
     * @param message The message to be printed.
     */
    public static void printWithNoIndents(String message) {
        printWithIndents(message, 0);
    }

    /**
     * Prints the given message with 1 indent.
     *
     * @param message The message to be printed.
     */
    public static void printWithOneIndent(String message) {
        printWithIndents(message, 1);
    }

    /**
     * Prints the given message with the given number of indents (spaces).
     *
     * @param message The message to be printed.
     * @param indents The number of indents.
     */
    public static void printWithIndents(String message, int indents) {
        cumulativeSubNodes.add(new DialogNode.SubNode(indents, message));
    }

    /**
     * Sends the reply message stored in stringBuilder to the UI window to be displayed.
     */
    public static void reply() {
        MainWindow mainWindow = MainWindow.getInstance();
        if (mainWindow == null) {
            return;
        }

        ArrayList<DialogNode.SubNode> nodesCopy = new ArrayList<>(cumulativeSubNodes);

        Platform.runLater(() -> mainWindow.addBotDialogNode(nodesCopy));
        cumulativeSubNodes.clear();
    }
}

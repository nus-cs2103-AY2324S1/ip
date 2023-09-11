package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Task;
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Provides a base structure for command implementations.
 */
public abstract class Command {
    /**
     * Enumeration representing different types of tasks.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public abstract String doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, Scene scene, VBox dialogContainer);

    /**
     * Extracts the task number from the user input.
     *
     * @param userInput The user input string.
     * @param command   The command keyword.
     * @return The extracted task number.
     */
    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }

    /**
     * Extracts the task description from the user input.
     *
     * @param userInput The user input string.
     * @param command   The command keyword.
     * @return The extracted task description.
     */
    public static String extractTaskDesc(String userInput, String command) {
        return userInput.substring(command.length()).trim();
    }
}

package duke.command;

import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

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

    /**
     * Executes the command operation.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    public abstract void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea);

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

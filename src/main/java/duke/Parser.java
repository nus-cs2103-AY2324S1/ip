package duke;

import java.io.IOException;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Processes the user input and carries out the next steps accordingly.
     * The shouldUpdateUndoTxtFile boolean ensures that the 'undo' command ignores commands that are not
     * worth 'undoing', e.g. 'list' command
     *
     * @param userInput User input
     * @param tasks TaskList
     * @param storage Storage
     */
    public static String parse(String userInput, TaskList tasks, Storage storage) {
        String output;
        boolean shouldUpdateUndoTxtFile = false;
        try {
            if (userInput.equals("list")) {
                output = tasks.displayList();
            } else if (userInput.equals("bye")) {
                output = Ui.handleExitUi();
            } else if (userInput.contains("mark")) {
                output = tasks.markTask(userInput);
                shouldUpdateUndoTxtFile = true;
            } else if (userInput.contains("todo")) {
                output = tasks.addTask("T", userInput);
                shouldUpdateUndoTxtFile = true;
            } else if (userInput.contains("deadline")) {
                output = tasks.addTask("D", userInput);
                shouldUpdateUndoTxtFile = true;
            } else if (userInput.contains("event")) {
                output = tasks.addTask("E", userInput);
                shouldUpdateUndoTxtFile = true;
            } else if (userInput.contains("delete")) {
                output = tasks.deleteTask(userInput);
                shouldUpdateUndoTxtFile = true;
            } else if (userInput.contains("find")) {
                output = tasks.findMatchingTasks(userInput.substring(5));
            } else if (userInput.contains("undo")) {
                tasks.undo(storage);
                output = Ui.handleUndoUi() + tasks.displayList();
            } else if (userInput.equals("help")) {
                output = tasks.displayUserGuide();
            } else {
                output = Ui.handleInvalidCommandUi();
            }
        } catch (DukeException | IOException | ClassNotFoundException e) {
            return e.getMessage();
        }
        storage.updateFile(tasks, shouldUpdateUndoTxtFile);
        return output;
    }
}

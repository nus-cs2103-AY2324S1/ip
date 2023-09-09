package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

/**
 * Represents a command to delete a task from the task list.
 * Parses the user input to determine which task to delete and removes it from the list.
 */
public class DeleteCommand extends Command {

    private String userInput;

    /**
     * Constructs a new DeleteCommand with the user input.
     *
     * @param userInput The user input string for specifying the task to delete.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the DeleteCommand. Parses the user input to determine which task to delete,
     * removes it from the task list, and updates the storage.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the delete task operation.
     * @throws MissingTaskIndexException If the task index is missing in the user input.
     * @throws InvalidTaskIndexException If the task index is invalid (out of range).
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = ui.deleteTaskReply(Parser.taskToDelete(userInput, taskList), taskList);
            storage.updateStorage(taskList);
            return output;
        } catch (MissingTaskIndexException e) {
            return ui.missingTaskIndexExceptionReply();
        } catch (InvalidTaskIndexException e) {
            return ui.invalidTaskIndexExceptionReply(taskList);
        }

    }
}



package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `UnmarkCommand` class represents a command to unmark a task as done.
 * It is responsible for parsing the user input, executing the unmark operation on the task list,
 * updating the storage, and providing a response message.
 */
public class UnmarkCommand extends Command {

    private String userInput;

    /**
     * Constructs a new `UnmarkCommand` object with the given user input.
     *
     * @param userInput The user input string containing the unmark command.
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the unmark command. It attempts to unmark the specified task as undone, updates the storage,
     * and returns a response message indicating the result.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the unmark operation.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = ui.unmarkTaskReply(Parser.taskToUnmark(userInput, taskList), taskList);
            storage.updateStorage(taskList);
            return output;
        } catch (MissingTaskIndexException e) {
            return ui.missingTaskIndexExceptionReply();
        } catch (InvalidTaskIndexException e) {
            return ui.invalidTaskIndexExceptionReply(taskList);
        }
    }
}



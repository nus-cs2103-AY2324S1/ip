package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `MarkCommand` class represents a command to mark a task as done.
 * It is responsible for parsing the user input, marking the specified task as done in the task list,
 * updating the storage, and providing a response message.
 */
public class MarkCommand extends Command {

    private String userInput;

    /**
     * Constructs a new `MarkCommand` object with the given user input.
     *
     * @param userInput The user input string containing the "Mark" command.
     */
    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the "Mark" command. It attempts to mark the specified task as done, updates the storage,
     * and returns a response message indicating the result.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of the "Mark" operation.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = ui.markTaskReply(Parser.taskToMark(userInput, taskList), taskList);
            storage.updateStorage(taskList);
            return output;
        } catch (MissingTaskIndexException e) {
            return ui.missingTaskIndexExceptionReply();
        } catch (InvalidTaskIndexException e) {
            return ui.invalidTaskIndexExceptionReply(taskList);
        }
    }
}

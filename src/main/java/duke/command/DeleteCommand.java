package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;

/**
 * The DeleteCommand class represents a command to delete a task
 * It parses the user input and handles exceptions related to invalid input.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Delete command, deleting a task in the task list.
     *
     * @param tasks   The task list in which the task is to be deleted.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage) throws InvalidArgumentException {
        String[] words = this.fullCommand.split(" ", 2);
        try {
            return tasks.deleteTask(Integer.parseInt(words[1]) - 1, storage);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("delete");
        }
    }
}

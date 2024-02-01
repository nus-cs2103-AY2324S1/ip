package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;

/**
 * The FindCommand class represents a command to search for tasks in the Duke application.
 * It searches for tasks that match a given keyword and displays the results to the user.
 */
public class FindCommand extends Command {

    /**
     * Constructs a new FindCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the find command, searching for tasks that match a given keyword.
     *
     * @param tasks   The task list to search for matching tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing the keyword.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage) {
        String[] words = this.fullCommand.split(" ", 2);
        super.validateArguments(words, "find");

        return tasks.findMatching(words[1]);
    }
}

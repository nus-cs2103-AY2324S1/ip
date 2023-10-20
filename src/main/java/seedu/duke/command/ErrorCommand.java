package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Represents an error if there are input errors by the user.
 *
 *  @author KAM JIA YUE
 *  @since 2023-09-12
 */
public class ErrorCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * Executes this error command.
     *
     * @param taskList User's task list.
     * @param ui The ui of Duke chat bot.
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @throws Exception If there are any user input errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

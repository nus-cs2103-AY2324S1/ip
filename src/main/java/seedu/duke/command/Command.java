package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Represents all sorts of commands input by the user.
 *
 *  @author KAM JIA YUE
 *  @since 2023-09-12
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param taskList User's task list.
     * @param ui The ui of Duke chat bot.
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @throws Exception If there are any user input errors.
     */
    public abstract void execute(TaskList taskList, Ui ui, String[] response) throws Exception;
}

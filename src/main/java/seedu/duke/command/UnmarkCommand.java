package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Represents the unmark command input by user.
 *
 *  @author KAM JIA YUE
 *  @since 2023-09-12
 */
public class UnmarkCommand extends EditCommand {

    private final String input;

    /**
     * The main constructor for this UnmarkCommand class.
     *
     * @param input User's input.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes this unmark command.
     *
     * @param taskList User's task list.
     * @param ui The ui of Duke chat bot.
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @throws Exception If there are any user input errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        int unmarkIndex = ui.unmark(input);
        assert unmarkIndex >= 0 : "Unmark index should be non-negative.";
        if (taskList.isOutOfRange(unmarkIndex)) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
        response[0] = taskList.unmark(unmarkIndex);
    }
}

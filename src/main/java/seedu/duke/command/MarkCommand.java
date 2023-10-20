package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Represents the mark command input by user.
 *
 *  @author KAM JIA YUE
 *  @since 2023-09-12
 */
public class MarkCommand extends EditCommand {

    private final String input;

    /**
     * The main constructor for this MarkCommand class.
     *
     * @param input User's input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     *
     * Executes this mark command.
     *
     * @param taskList User's task list.
     * @param ui The ui of Duke chat bot.
     * @param response The String representation of Duke chat bot's response after adding this task.
     * @throws Exception If there are any user input errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        int markIndex = ui.mark(input);
        assert markIndex >= 0 : "Mark index should be non-negative.";
        if (taskList.isOutOfRange(markIndex)) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to be marked.");
        }
        response[0] = taskList.mark(markIndex);
    }
}

package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class UnmarkCommand extends EditCommand {

    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        int unmarkIndex = ui.unmark(input);
        if (taskList.isOutOfRange(unmarkIndex)) {
            throw new DukeException("☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
        response[0] = taskList.unmark(unmarkIndex);
    }
}

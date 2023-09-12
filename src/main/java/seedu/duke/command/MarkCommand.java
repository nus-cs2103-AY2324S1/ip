package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class MarkCommand extends EditCommand {

    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

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

package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, String[] response) throws Exception {
        String toFind = ui.find(input);
        response[0] = taskList.find(toFind);
    }
}

package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;

/**
 * FindCommand finds the task in the task list with the keyword the user inputs.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public FindCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String keyword = input.split(" ")[1];
            return ui.printFindTask(taskList, keyword);
        } else {
            throw new EmptyInputException("find");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

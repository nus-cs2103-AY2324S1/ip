package command;

import duke.TaskList;
import duke.Ui;

import exception.EmptyInputException;

public class FindCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    public FindCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String keyword = input.split(" ")[1];
            taskList.findTask(keyword);
        } else {
            throw new EmptyInputException("find");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

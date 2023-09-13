package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;
import task.Task;

/**
 * FindCommand finds the task in the task list with the keyword the user inputs.
 */
public class FindCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String keyword = input.split(" ")[1];
            ArrayList<Task> matchingTasks = taskList.findTask(keyword);
            return ui.printFindTask(matchingTasks, keyword);
        } else {
            throw new EmptyInputException("find");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

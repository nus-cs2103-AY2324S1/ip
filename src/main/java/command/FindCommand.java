package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find task matching keyword.
 */
public class FindCommand extends Command {

    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        String input = keyword.toLowerCase();
        String action = tasks.findMatchingTasks(input);

        if (action.equals("")) {
            return "No task matching keyword found!";
        } else {
            return "Here are the matching tasks in your list:\n" + action;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}


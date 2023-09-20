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

        return action;
    }

}


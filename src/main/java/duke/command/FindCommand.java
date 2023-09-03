package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find tasks that match a search term.
 */
public class FindCommand extends Command {
    String searchTerm;

    /**
     * Initialises the find command with a search term.
     * 
     * @param searchTerm The search term to find tasks with.
     */
    public FindCommand(String searchTerm) {
        super(CommandType.FIND);
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the find command.
     * 
     * @param tasks The task list to find tasks from.
     * @param ui The ui to display the matching tasks.
     */
    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.findTasks(searchTerm);
        ui.listTasks(matchingTasks, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            FindCommand findCommand = (FindCommand) obj;
            return findCommand.searchTerm.equals(this.searchTerm);
        }
        return false;
    }
}

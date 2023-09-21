package duke.command;

import java.util.ArrayList;

import duke.*;

/**
 * Represents a command that finds tasks containing a specific keyword.
 * This command will search through the task list for tasks that contain
 * the keyword in their description and display the matching tasks to the user.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword The keyword to search for within the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the task list for tasks
     * that contain the specified keyword in their description.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to read/write tasks from/to the data file.
     * @return A string message detailing the matching tasks.
     * @throws DukeException If any issue occurs during the operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}

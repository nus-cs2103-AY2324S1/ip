package duke.command;

import duke.*;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initializes a new instance of the FindCommand class.
     *
     * @param keyword The keyword to search for within tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain
     * the specified keyword and displaying them to the user.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The UI instance to communicate with the user.
     * @param storage The storage instance to read/write to the data file.
     * @throws DukeException If any issue occurs while processing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}

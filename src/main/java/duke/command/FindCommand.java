package duke.command;

import java.util.ArrayList;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The FindCommand class represents a command to search for tasks containing a specific keyword.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructs a FindCommand with the specified user input.
     *
     * @param input The user input containing the keyword to search for.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "find" command by searching for tasks containing a specific keyword.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface for displaying the search results.
     * @param storage The storage (not used in this command).
     * @return A string containing the list of tasks that match the search keyword.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String keyword = Parser.extractKeyword(this.input);
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}

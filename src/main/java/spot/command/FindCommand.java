package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

/**
 * Represents a command to find a task with a certain keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword Keyword to match tasks to.
     */
    public FindCommand(String keyword) {
        assert !keyword.isEmpty();
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.findTasks(ui, keyword);
    };

    /**
     * Checks if the FindCommand is an ExitCommand.
     *
     * @return Boolean representing whether the FindCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

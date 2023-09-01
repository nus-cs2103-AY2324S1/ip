package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * Class of commands that filters the existing list.
 */
public class FilterCommand extends Command {
    /**
     * type contains the type of command.
     *
     * keyword contains the keyword to filter.
     */
    String type;
    String keyword;

    /**
     * Constructor for the FilterCommand class.
     *
     * @param type The type of command.
     * @param keyword The keyword to filter.
     */
    public FilterCommand(String type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }

    /**
     * Executes the command based on user input.
     *
     * @param tasks List of tasks in taskList.
     * @param ui Instance of the user interface.
     * @param storage Instance of the storage.
     * @throws DukeException Invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String list = tasks.filter(keyword);
            ui.showFilteredList(list);
        } catch (Exception ex) {
            throw new DukeException("I'm afraid such a task do not exist.");
        }
    }

    /**
     * Returns whether to terminate the bot.
     *
     * @return Whether to terminate the bot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }
}

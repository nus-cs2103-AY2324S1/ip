package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a FindCommand class that deals with the command to find a task.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword keyword to be searched for in the description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the list of tasks that contains the keyword.
     *
     * @param taskList list of tasks that contains the keyword.
     * @param storage  storage object to save the list of tasks.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        return GobbleMessage.getDukeDialog(taskList.findTask(this.keyword).toString(), "Find");
    }
}

package noac.command;

import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;

/**
 * For executing the list command.
 */
public class ListCommand extends Command {

    /**
     * Display the list to the user using UI class.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}

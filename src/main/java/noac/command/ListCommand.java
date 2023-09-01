package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}

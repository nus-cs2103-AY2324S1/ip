package tasket.command;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for list command.
 */
public class ListCommand extends Command {

    /**
     * The constructor for list command.
     */
    public ListCommand() {
        super("");
    }

    /**
     * Show all available tasks.
     * Collects the tasks in string format, then show it in ui.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return List of all tasks.
     * @throws TasketException Does not throw the exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        String[] taskStrings = new String[taskList.size()];

        for (int i = 0; i < taskStrings.length; i++) {
            taskStrings[i] = taskList.getTaskString(i);
        }

        return ui.showTaskList(taskStrings);
    }
}

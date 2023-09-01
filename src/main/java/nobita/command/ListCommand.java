package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 *  Class that encapsulates ListCommand which extends from Command.
 *
 *  @author Zheng Chenglong
 *
 */
public class ListCommand extends Command {

    /**
     *  Command that executes listing of all tasks.
     *
     *  @param tasks Contains all current tasks.
     *  @param ui Ui for interacting with user.
     *  @param storage Storage that the data file is stored in.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showAllTask();
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}

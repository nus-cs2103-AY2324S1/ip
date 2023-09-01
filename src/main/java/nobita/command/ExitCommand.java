package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 * Class that encapsulates ExitCommand which extends from Command.
 *
 * @author Zheng Chenglong
 *
 */
public class ExitCommand extends Command {

    /**
     * Command that executes exit of program.
     *
     * @param tasks Contains all current tasks.
     * @param ui Ui for interacting with user.
     * @param storage Storage that the data file is stored in.
     * @throws NobitaException If error occurs when writing to local files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        storage.writeFile(tasks);
        ui.exitMessage();
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    };
}

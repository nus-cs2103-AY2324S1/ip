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
     * @return A String representing the output message.
     * @throws NobitaException If error occurs when writing to local files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        storage.writeFile(tasks);
        return "Bye. Hope to see you again soon!";
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

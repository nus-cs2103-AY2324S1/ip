package tasket.command;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for bye command.
 */
public class ByeCommand extends Command {

    /**
     * The constructor for bye command.
     */
    public ByeCommand() {
        super("");
    }

    /**
     * Show goodbye message.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return Goodbye message.
     * @throws TasketException Does not throw the exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        return ui.showGoodBye();
    }

    /**
     * @inheritDoc
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

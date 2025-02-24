package tasket.command;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.StorageInterface;
import tasket.ui.Ui;

/**
 * The class for bye command.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a bye command.
     */
    public ByeCommand() {
        super("");
    }

    /**
     * Shows goodbye message.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return Goodbye message.
     * @throws TasketException Does not throw the exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, StorageInterface storage) throws TasketException {
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

package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Task;

/**
 * UnmarkCommand inherits from Commands.
 */
public class UnmarkCommand extends Commands {
    private final int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as not done and saved the task to the saved file.
     * @param allyList
     * @param ui
     * @param storage
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) {
        try {
            Task task = allyList.getTask(index);
            storage.appendToFile(task);
            return ui.showNotMarked(task);
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Task;

/**
 * MarkCommand inherits from Commands.
 */
public class MarkCommand extends Commands {

    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done and save the marked task to the saved file.
     *
     * @param allyList
     * @param ui
     * @param storage
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) {
        try {
            assert allyList != null;
            assert ui != null;
            assert storage != null;
            Task task = allyList.getTask(index);
            storage.appendToFile(task);
            return ui.showMarked(task);
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

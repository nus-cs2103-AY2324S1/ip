package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Task;

/**
 * DeleteCommand inherits from Commands.
 */
public class DeleteCommand extends Commands {

    private final int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Calls showDelete function in Ui.
     * Deletes the task from allyList.
     *
     * @param allyList
     * @param ui
     * @param storage
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) {
        try {
            Task deletedTask = allyList.getTask(index);
            allyList.deleteElement(index);
            return ui.showDelete(deletedTask, allyList.getSize());
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}

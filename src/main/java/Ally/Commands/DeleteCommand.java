package Ally.Commands;

import Ally.*;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;

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
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            ui.showDelete(allyList.getTask(index), allyList.getSize() - 1);
            allyList.deleteElement(index);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}

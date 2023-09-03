package Ally.Commands;

import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Storage;
import Ally.Ui;

public class ListCommand extends Commands {

    /**
     * Calls the showList function in Ui.
     *
     * @param allyList
     * @param ui
     * @param storage
     * @throws AllyException
     */
    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        ui.showList(allyList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

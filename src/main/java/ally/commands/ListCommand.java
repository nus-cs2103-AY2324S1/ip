package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;

/**
 * ListCommand inherits from Commands.
 */
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
    public String run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        return ui.showList(allyList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

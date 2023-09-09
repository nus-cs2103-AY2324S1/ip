package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;

/**
 * ExitCommand inherits from Commands.
 */
public class ExitCommand extends Commands {

    /**
     * Calls the bye function in Ui.
     *
     * @param allyList
     * @param ui
     * @param storage
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) {
        try {
            return ui.bye();
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

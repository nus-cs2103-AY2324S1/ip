package Ally.Commands;

import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Storage;
import Ally.Ui;

public class ExitCommand extends Commands {

    /**
     * Calls the bye function in Ui.
     *
     * @param allyList
     * @param ui
     * @param storage
     */
    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            ui.bye();
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

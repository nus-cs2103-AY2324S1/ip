package Ally.Commands;

import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Storage;
import Ally.Ui;

public class ListCommand extends Commands {

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        ui.showList(allyList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

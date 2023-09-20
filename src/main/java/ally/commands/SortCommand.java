package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;

public class SortCommand extends Commands {
    public String run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        allyList.sortDeadlines();
        return ui.showSortedList(allyList.getSortedDeadline());
    }

    public boolean isExit() {
        return false;
    }
}

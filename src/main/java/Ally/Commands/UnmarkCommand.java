package Ally.Commands;

import Ally.*;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Tasks.Task;

public class UnmarkCommand extends Commands {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            Task task = allyList.getTask(index);
            allyList.unMarkDone(index);
            ui.showNotMarked(task);
            storage.appendToFile(task);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

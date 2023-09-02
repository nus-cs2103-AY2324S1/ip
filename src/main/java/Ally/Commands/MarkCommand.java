package Ally.Commands;

import Ally.*;

import java.util.Map;

public class MarkCommand extends Commands {

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        try {
            Task task = allyList.getTask(index);
            allyList.markAsDone(index);
            ui.showMarked(task);
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

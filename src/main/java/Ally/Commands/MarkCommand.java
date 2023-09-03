package Ally.Commands;

import Ally.*;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Tasks.Task;

public class MarkCommand extends Commands {

    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done and save the marked task to the saved file.
     *
     * @param allyList
     * @param ui
     * @param storage
     */
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

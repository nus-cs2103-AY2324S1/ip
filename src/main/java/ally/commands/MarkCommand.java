package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Task;

/**
 * MarkCommand inherits from Commands.
 */
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

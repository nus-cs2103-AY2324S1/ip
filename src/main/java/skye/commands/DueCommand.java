package skye.commands;

import java.time.LocalDate;

import skye.data.ListManager;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command to check tasks due on a certain date.
 */
public class DueCommand extends Command {

    public static final String COMMAND_WORD = "due";
    private final LocalDate date;

    public DueCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to check due tasks
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        return ui.showTasksDueOn(date, listManager.getTaskList().showTasksDueOn(date));
    }
}

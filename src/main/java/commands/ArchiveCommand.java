package commands;

import data.Actions;
import data.Save;
import duke.DukeException;
import ui.UI;

/**
 * Represents a command to archive tasks.
 * When executed archives all current tasks and provides a fresh list.
 */
public class ArchiveCommand extends Command {
    private final Save saveManager;

    /**
     * Initializes a save.
     *
     * @param saveManager The save manager used to archive tasks.
     */
    public ArchiveCommand(Save saveManager) {
        this.saveManager = saveManager;
    }

    /**
     * Executes the ArchiveCommand by archiving all tasks.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException To handle any exceptions during archiving.
     */
    @Override
    public String executeCommand(UI ui, Actions actionList) throws DukeException {
        saveManager.archiveTasks(actionList.list());
        actionList.list().clear();
        return " Tasks archived, you're now working with a fresh list.";
    }
}

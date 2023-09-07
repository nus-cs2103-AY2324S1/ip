package skye.commands;

import java.time.LocalDate;

import skye.data.TaskList;
import skye.storage.Storage;
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
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.showTasksDueOn(date, taskList.showTasksDueOn(date));
    }
}

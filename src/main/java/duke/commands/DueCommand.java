package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

import java.time.LocalDate;

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
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showTasksDueOn(date, taskList.showTasksDueOn(date));
    }
}
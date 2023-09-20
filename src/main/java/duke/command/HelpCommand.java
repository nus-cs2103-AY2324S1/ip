package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class HelpCommand extends Command{

    /**
     * Constructs a HelpCommand object with the specified task.
     *
     * @param task The task associated with the command.
     */
    public HelpCommand(Task task) {
        super(task);
    }

    /**
     * Executes the command and displays help information through user interface.
     *
     * @param tasks   The task list (not used).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
        ui.showFeatures();
    }
}

package potato.command;

import java.io.IOException;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The ClearCommand class represents a command for clearing the tasks in the task list.
 * It extends the Command class and specifies the behavior of executing a "clear" command.
 */
public class ClearCommand extends Command {

    /**
     * Constructs a ClearCommand object.
     */
    public ClearCommand() {
        super.isExit = false;
    }

    /**
     * Executes the clear command, which clears the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of clearing the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.clear();
    }
}

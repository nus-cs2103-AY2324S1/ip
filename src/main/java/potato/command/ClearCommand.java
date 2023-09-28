package potato.command;

import potato.*;

import java.io.IOException;

public class ClearCommand extends Command {
    public ClearCommand() {
        super.isExit = false;
    }

    /**
     * Executes the clear command, which clears the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.clear();
    }
}
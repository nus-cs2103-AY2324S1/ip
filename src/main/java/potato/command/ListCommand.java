package potato.command;

import potato.*;

import java.io.IOException;

public class ListCommand extends Command {
    public ListCommand() {
        super.isExit = false;
    }

    /**
     * Executes the list command, which displays the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }
}

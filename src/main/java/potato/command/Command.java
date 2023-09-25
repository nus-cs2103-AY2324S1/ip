package potato.command;

import java.io.IOException;

import potato.*;

public abstract class Command {
    protected boolean isExit;
    protected String input;

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

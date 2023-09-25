package potato.command;

import java.io.IOException;

import potato.*;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the mark command, which marks a task in the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.mark(input, storage);
    }
}

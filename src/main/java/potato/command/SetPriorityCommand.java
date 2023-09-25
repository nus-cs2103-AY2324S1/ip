package potato.command;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

import java.io.IOException;

public class SetPriorityCommand extends Command {
    public SetPriorityCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the priority command, which sets the priority of a task in the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.setPriority(input, storage);
    }
}

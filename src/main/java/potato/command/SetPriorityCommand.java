package potato.command;

import java.io.IOException;

import potato.PotatoException;
import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The SetPriorityCommand class represents a command for setting the priority of a task in the task list.
 * It extends the Command class and specifies the behavior of executing a "set priority" command.
 */
public class SetPriorityCommand extends Command {

    /**
     * Constructs a SetPriorityCommand object with the provided user input.
     *
     * @param input The user input specifying the index and priority of the task to be set.
     */
    public SetPriorityCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the priority command, which sets the priority of a task in the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of setting the priority of the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            return tasks.setPriority(input, storage);
        } catch (PotatoException e) {
            return e.getMessage();
        }
    }
}

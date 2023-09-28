package potato.command;

import java.io.IOException;

import potato.PotatoException;
import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The UnmarkCommand class represents a command for unmarking a task in the task list.
 * It extends the Command class and specifies the behavior of executing an "unmark" command.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a UnmarkCommand object with the provided user input.
     *
     * @param input The user input specifying the index of the task to be unmarked.
     */
    public UnmarkCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the unmark command, which unmarks a task in the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of unmarking the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            return tasks.unmark(input, storage);
        } catch (PotatoException e) {
            return e.getMessage();
        }
    }
}

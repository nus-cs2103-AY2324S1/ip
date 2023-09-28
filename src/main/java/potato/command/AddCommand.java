package potato.command;

import java.io.IOException;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The AddCommand class represents a command for adding a task to the task list.
 * It extends the Command class and specifies the behavior of executing an "add" command.
 */
public class AddCommand extends Command {

    /**
     * Constructs an AddCommand object with the provided user input.
     *
     * @param input The user input specifying the task to be added.
     */
    public AddCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the add command, which adds a task to the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return tasks.add(input, storage);
    }
}

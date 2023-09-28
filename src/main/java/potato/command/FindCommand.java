package potato.command;

import java.io.IOException;

import potato.PotatoException;
import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The FindCommand class represents a command for finding tasks in the task list.
 * It extends the Command class and specifies the behavior of executing a "find" command.
 */
public class FindCommand extends Command {

    /**
     * Constructs an FindCommand object with the provided user input.
     *
     * @param input The user input specifying the keyword to match to the tasks.
     */
    public FindCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the find command, which finds task in the task list that matches the keyword.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of finding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            return tasks.find(input);
        } catch (PotatoException e) {
            return e.getMessage();
        }
    }
}

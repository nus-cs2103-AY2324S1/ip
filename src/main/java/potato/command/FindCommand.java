package potato.command;

import java.io.IOException;

import potato.*;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the find command, which finds task in the task list that matches the keyword.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of adding the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        return tasks.find(input);
    }
}

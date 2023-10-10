package potato.command;

import java.io.IOException;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The ListCommand class represents a command for listing the tasks in the task list.
 * It extends the Command class and specifies the behavior of executing a "list" command.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super.isExit = false;
    }

    /**
     * Executes the list command, which displays the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of listing the tasks.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }
}

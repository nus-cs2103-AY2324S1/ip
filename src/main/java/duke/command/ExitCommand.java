package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeStorageException;

/**
 * Represents a command that exits Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command: displays a "Goodbye" message and exits Duke.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            storage.saveData(tasks);
            return "Bye. Hope to see you again soon!";
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;

import oscar.exception.OscarException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final String details;

    /**
     * Instantiates an unmark command.
     *
     * @param details Task number to be marked.
     */
    public UnmarkCommand(String details){
        super(false);
        this.details = details;
    }

    /**
     * Marks a task as not done using the task number.
     *
     * @param tasks ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Failure to validate task number.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws OscarException {
        int index = validateInt(details, tasks);
        String currentTask = tasks.unmark(index);
        storage.save(tasks);
        System.out.println("Oscar has marked this task as not done yet:\n");
        System.out.println(currentTask + "\n");
    }
}

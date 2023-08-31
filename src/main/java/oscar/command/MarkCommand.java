package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.essential.Ui;

import oscar.exception.OscarException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String details;

    /**
     * Instantiates a mark command.
     * @param details Task number to be marked.
     */
    public MarkCommand(String details) {
        super(false);
        this.details = details;
    }

    /**
     * Marks a task as done using the task number.
     * @param tasks ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Failure to validate task number.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws OscarException {
        int index = validateInt(details, tasks);
        String currentTask = tasks.mark(index);
        storage.save(tasks);
        System.out.println("Nice! Oscar has marked this task as done:\n");
        System.out.println(currentTask + "\n");
    }
}

package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.exception.OscarException;

/**
 * Command to find a task in the task list.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a find command.
     *
     * @param keyword String used to find tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Find a task by searching for a keyword.
     *
     * @param tasks   ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Failure to execute command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws OscarException {
        validate();
        tasks.find(keyword);
    }

    /**
     * Validates keyword used to find task.
     *
     * @throws OscarException Keyword is blank or not within 200 characters.
     */
    public void validate() throws OscarException {
        if (keyword.isEmpty()) {
            throw new OscarException("Sorry! The keyword of a find command cannot be empty.\n");
        } else if (keyword.length() > 200) {
            throw new OscarException("Sorry! The keyword of a find command cannot exceed 200 characters.\n");
        }
    }
}

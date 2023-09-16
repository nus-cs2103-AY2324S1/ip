package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.exceptions.IncompleteInputException;
import sillybot.tasks.TaskList;

/**
 * Represents a FindCommand object that handles the find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand object.
     *
     * @return The String representation of the FindCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteInputException {
        String response;

        try {
            response = tasks.findTasks(keyword);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new IncompleteInputException("find what eh! ");
        }

        return response;
    }
}

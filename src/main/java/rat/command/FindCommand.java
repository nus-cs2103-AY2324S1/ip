package rat.command;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a Find command.
 * A find command searches the task list for tasks that contain a keyword, and prints the matching tasks to the user.
 */
public class FindCommand extends RatCommand {

    /**
     * The keyword to search for.
     */
    private String keyword;

    /**
     * Constructor for a FindCommand.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param input The user's input.
     */
    public FindCommand(RatTaskManager ratTaskManager, String input) {
        super(ratTaskManager);
        this.keyword = input.substring(5);
    }

    @Override
    public String getResponse() {
        return this.ratTaskManager.printFoundTasks(this.keyword);
    }

    /**
     * Executes the FindCommand.
     */
    @Override
    public void execute() {
        this.ratTaskManager.printFoundTasks(this.keyword);
    }
}

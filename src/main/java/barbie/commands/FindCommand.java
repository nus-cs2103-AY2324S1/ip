package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;

/**
 * Represents the command when a "find" is called by the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs an instance of the FindCommand and saves the keyword to search.
     * @param keyword the keyword to match with
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.isExit = false;

    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        return Ui.findTasks(taskList, this.keyword);
    }
}

package command;

import task.Task;
import task.TaskList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command that finds tasks by a given keyword.
 */
public class FindCommand extends Command {

    /** The keyword to search for. */
    private final String keyword;

    /**
     * Initializes a new instance of the FindCommand with the given keyword.
     *
     * @param input The user's input to search tasks by.
     */
    public FindCommand(String input) {
        super(input);
        this.keyword = input.substring(5).trim();
    }

    /**
     * Executes the find command, filtering the tasks by the keyword.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The task storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>(tasks.getList());
        matchingTasks.removeIf(task -> !task.getDescription().contains(keyword));

        if (matchingTasks.isEmpty()) {
            ui.showNotFound();
        } else {
            ui.showTaskListHeader();
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showTask(i, matchingTasks.get(i));
            }
        }
    }

    /**
     * Specifies the next action after executing the find command.
     *
     * @return false, indicating not to exit the application after this command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

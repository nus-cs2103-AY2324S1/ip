package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to find tasks within the task list.
 */
public class FindCommand extends Command {

    /** The String to search for. */
    private String findStr;

    /**
     * Creates a FindCommand with a specified search string.
     *
     * @param findStr The search string to search the TaskList for.
     */
    public FindCommand(String findStr) {
        this.findStr = findStr;
    }

    /**
     * Finds tasks in the TaskList with the given search string, and prints the matching tasks. Prints a message if no
     * matching tasks are found.
     *
     * @param tasks The task list to search using the search keyword.
     * @param ui The UI to print the output onto.
     * @param storage The storage to save and update tasks (if needed).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(findStr);
        if (foundTasks.getSize() > 0) {
            ui.print("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                System.out.println("\t " + (i + 1) + "." + foundTasks.getTaskString(i + 1));
            }
        } else {
            ui.print("There are no tasks with the given keyword!");
        }
    }

    /**
     * Gets the command type for the FindCommand.
     *
     * @return Find.
     */
    @Override
    public String getCommandType() {
        return "Find";
    }
}

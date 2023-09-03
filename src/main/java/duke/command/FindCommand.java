package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Represents a command to search for tasks that match a given keyword.
 */
public class FindCommand extends Command{
    private String keyword;
    private boolean isExit = false;

    /**
     * Constructs a FindCommand instance with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword and displaying the results.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "Here are the matching tasks in your list: ";
        int count = 1;

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);

            if(task.getDescription().contains(this.keyword)) {
                res += "\n" + count + ". " + task;
                count++;
            }
        }

        ui.printMessage(res);
    }

    /**
     * Returns whether executing this command should result in exiting the program.
     *
     * @return true if the command should result in program exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

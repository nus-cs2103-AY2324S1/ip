package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    /**
     * Pattern to be searched.
     */
    private final String pattern;

    /**
     * Constructor of FindCommand.
     * @param pattern Pattern to be searched
     */
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param ui The user interface
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "There are no matching tasks in your list!";
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).toString().contains(pattern)) {
                if (count == 0) {
                    sb.append("Here are the matching tasks in your list:\n");
                }
                sb.append((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
                count++;
            }
        }
        if (count == 0) {
            return "There are no matching tasks in your list!";
        }
        return sb.toString();
    }
}

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showCommandMessage("     There are no matching tasks in your list!\n");
        }
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).toString().contains(pattern)) {
                if (count == 0) {
                    System.out.print(Ui.SEPARATOR);
                    System.out.println("     Here are the matching tasks in your list:");
                }
                System.out.println("     " + (i + 1) + ". " + tasks.getTask(i).toString());
                count++;
            }
        }
        if (count > 0) {
            System.out.print(Ui.SEPARATOR);
        }
        if (count == 0) {
            ui.showCommandMessage("     There are no matching tasks in your list!\n");
        }
    }
}

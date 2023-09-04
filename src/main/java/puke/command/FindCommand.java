package puke.command;

import puke.TaskList;
import puke.Ui;

/**
 * A Command class that when executed, looks for tasks in the task list that match the keywords provided.
 */
public class FindCommand extends Command {
    private final String key;

    /**
     * Creates a new FindCommand
     * @param rest the rest of the input line.
     */
    public FindCommand(String rest) {
        super(false, true);
        this.key = rest;
    }

    /**
     * Executes the command, printing out the corresponding message from the UI while printing each matching
     * task in the task list.
     * @param tl the task list
     * @param ui the UI
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            System.out.println(ui.find());
            System.out.println(tl.find(this.key));
            System.out.println(Ui.separator());
        } catch (Exception PukeException) {
            System.out.println(Ui.errorMessage());
            System.out.println(Ui.separator());
        }
    }
}

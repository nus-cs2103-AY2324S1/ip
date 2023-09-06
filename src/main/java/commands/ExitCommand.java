package commands;

import functional.TaskList;
import functional.Ui;

/**
 * A command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a `ExitCommand` object.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the command to exit the application and displays a farewell message.
     *
     * @param tasks The task list (not used in this command).
     * @param ui   The user interface.
     * @param marked always false - intended for use by AddCommand
     * @param load always false - intended for use by AddCommand
     */
    public void execute(TaskList tasks, Ui ui, boolean marked, boolean load) {
        System.out.println(ui.showLine() + "\n" +
                " Bye. Hope to see you again soon!\n"
                + ui.showLine());
        super.hasExit = true;
    }
}

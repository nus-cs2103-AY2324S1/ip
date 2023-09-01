package command;

import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 *
 * @author Ho Khee Wei
 */
public class CmdBye extends Command {

    /**
     * Executes the command to exit the application and displays a farewell message.
     *
     * @param taskList Not used in this command.
     * @param ui       The user interface for displaying the farewell message.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye meow! Hope to see you again soon!");
    }

}

package command;

import common.Message;
import exceptions.ThorndikeException;
import task.TaskList;
import ui.ModeControl;

/**
 * Represents a command toggle between light mode and dark mode.
 *
 * @author Ho Khee Wei
 */
public class CmdToggle extends Command {

    /**
     * Executes the command to switch between light mode and dark mode.
     *
     * @param taskList Not used in this command
     * @return response to the user.
     * @throws ThorndikeException if there an error occurs during execution.
     */
    @Override
    public String execute(TaskList taskList) throws ThorndikeException {
        String mode = ModeControl.toggle();
        return String.format(Message.TOGGLE, mode);
    }

}

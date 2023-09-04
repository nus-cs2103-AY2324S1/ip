package command;

import javafx.application.Platform;
import task.TaskList;

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
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        Platform.exit();
        return "";
    }

}

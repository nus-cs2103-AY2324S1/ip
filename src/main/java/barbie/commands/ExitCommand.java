package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;

/**
 * Reoresents the command when a "bye" is called by the user.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an instance of the ExitCommand.
     * Sets the isExit variable to true.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return String to be returned to the user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        return Ui.exit();

    }
}

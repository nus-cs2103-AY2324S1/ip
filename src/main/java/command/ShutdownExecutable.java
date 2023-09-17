package command;
import duke.TaskList;
import duke.UserInterface;

/**
 * Represents a call to shut down.
 */
public class ShutdownExecutable implements Executable {
    /**
     * Outputs a boolean that indicates a shutdown command.
     * @param list the list that contains the task. (not necessary)
     * @param ui the interface that prints out any necessary outputs.
     * @return true, since the execution ends the bot.
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output("Shutting down now!");
        return true;
    }
}

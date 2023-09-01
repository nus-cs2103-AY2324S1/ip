package command;
import duke.TaskList;
import duke.UserInterface;

/**
 * Represents a call for the bot to be shut down, which will be interpreted by Duke.
 */
public class ShutdownCommand implements Commandable {
    /**
     * Indicates that the bot is to be shut down.
     * @param list the list that contains the task. (not necessary)
     * @param ui the interface that prints out any necessary outputs.
     * @return true, since the execution ends the bot.
     */
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output("Shutting down now!");
        return true;
    }
}

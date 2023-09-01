package command;
import duke.TaskList;
import duke.UserInterface;

/**
 * Represents a command that outputs the list of tasks.
 */
public class ListCommand implements Commandable {
    /**
     * Prints out the list.
     * @param list the list to be printed
     * @param ui the interface that prints out the list.
     * @return false, since the execution does not end the bot.
     */
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output(list.listString());
        return false;
    }
}

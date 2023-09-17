package command;
import duke.TaskList;
import duke.UserInterface;

/**
 * Represents a command that prints a list of functions.
 */
public class HelpExecutable implements Executable {
    /**
     * Provides a string for the interface to print, providing a list of helpful commands.
     * @param list the associated list(not needed).
     * @param ui the interface that prints out the help guide.
     * @return false, since the execution does not end the bot.
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output("For the release v0.2.0-limited-release only \n"
                + "Here's the guidelines for all functions.\n"
                + "help: lists this help guide\n"
                + "list: lists all tasks\n"
                + "bye: ends bot\n"
                + "mark: mark (int x); marks indicated task\n"
                + "unmark: unmark (int x); unmarks indicated task\n"
                + "event: event (String name) /from (Date start) /to (Date end); creates event\n"
                + "todo: todo (String name); creates todo\n"
                + "deadline: deadline (String name) /by (Date deadline); creates deadline\n"
                + "delete: delete (int x); deletes the indicated task\n"
                + "find: find (String keyword); finds any tasks that contain the keyword\n"
                + "rebind: rebind (String sourceBinding) /to (String customBinding); adds an alias for a command\n"
                + "unbind: unbind (String customBinding); removes the alias\n"
                + "For more info, visit the user guide at https://ruiyangzh.github.io/ip."
        );
        return false;
    }
}

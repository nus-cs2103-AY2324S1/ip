package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Represents a command to exit the application.
 * This command displays a goodbye message to the user and indicates that the application should be exited.
 */
public class Bye extends Command {

    /**
     * Constructs a Bye command with the given input string.
     *
     * @param s The input string (not used in this command).
     */
    public Bye(String s) {
        super(s);
    }

    /**
     * Executes the Bye command.
     * Displays a goodbye message to the user.
     *
     * @param lst The task list (not used in this command).
     * @param io The user interface handling input and output.
     * @param s The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList lst, UI io, Storage s) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return {@code true} indicating that this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

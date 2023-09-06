package duke.commands;

import duke.tasks.TaskList;
import duke.ui.CliUi;

/**
 * Represents a command that the user has inputted.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public ExitCommand(TaskList taskList, String args) {
        super(CommandType.EXIT, taskList, args);
    }

    /**
     * Bids goodbye to the user.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        CliUi.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }
}

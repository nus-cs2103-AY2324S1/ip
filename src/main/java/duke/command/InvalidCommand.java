package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an InvalidCommand object using the superclass constructor.
     */
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    /**
     * Displays the invalid command message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @return The response to the user input.
     */
    public String execute(TaskList tasks, Ui ui) {
        return ui.showInvalidCommandMessage();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidCommand;
    }
}

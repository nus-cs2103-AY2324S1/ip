package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

/**
 * Represents a command that exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand object using the superclass constructor.
     */
    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * Displays the exit message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.showEndMessage();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}

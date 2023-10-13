package duke.commands;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to print out the help message
 */
public class HelpCommand extends Command {

    /**
     * Represents a constructor for HelpCommand object
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the command to print out the help message
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        return ui.helpList();
    }
}

package duke.commands;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand object
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the command to print out the help message
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        return ui.helpList();
    }
}

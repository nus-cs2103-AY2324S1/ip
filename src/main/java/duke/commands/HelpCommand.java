package duke.commands;

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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.helpList();
    }
}

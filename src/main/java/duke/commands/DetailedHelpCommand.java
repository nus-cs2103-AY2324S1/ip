package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class DetailedHelpCommand extends HelpCommand {
    private int helpNumber;

    /**
     * Constructor for DetailedHelpCommand object
     */
    public DetailedHelpCommand(int helpNumber) {
        super();
        this.helpNumber = helpNumber;
    }

    /**
     * Executes the command to print out the sample data for the user to learn
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        output += ui.detailedHelp(helpNumber);
        return output;
    }
}

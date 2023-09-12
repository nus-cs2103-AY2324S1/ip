package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command {

    /**
     * Constructor of the ListCommand object
     */
    public ListCommand() {
        super();
    }

    /**
     * Execute the ListCommand and returns a string
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        ui.printLine();
        output += tasks.printList();
        return output;
    }
}

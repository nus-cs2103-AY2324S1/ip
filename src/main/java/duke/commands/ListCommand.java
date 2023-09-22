package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

/**
 * Represents a command to list out all the tasks
 */
public class ListCommand extends Command {
    private String input;

    /**
     * Represents a constructor of the ListCommand object
     */
    public ListCommand(String input) {
        super();
        this.input = input;
    }

    /**
     * Executes the ListCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException{
        String[] split = input.split(" ");
        if (split.length > 1) {
            throw new UnknownCommandException("I am sorry, I do not get what you mean.");
        }
        String output = "";
        ui.printLine();
        output += tasks.printList();
        return output;
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class that represents the user command to find tasks based on specified keyword
 */
public class FindCommand extends Command {
    private String keyString;

    public FindCommand(String keyString) {
        this.keyString = keyString;
    }
    /**
     * A method that executes the command that user gave
     * @param tasks TaskList containing all existing Task objects
     * @param ui UI interface that is used to print messages to the terminal
     * @param storage Storage object that houses database of the program
     */
    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.find(tasks, this.keyString);
    }
}

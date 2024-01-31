package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * A class that represents the user command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * A method that executes the command that user gave.
     * @params tasks TaskList containing all existing Task objects.
     * @params ui UI interface that is used to print messages to the terminal.
     * @params storage Storage object that houses database of the program.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return "Bye. Hope to see you again soon!\n";
    }
}

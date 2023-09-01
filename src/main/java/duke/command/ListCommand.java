package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class represents a command to list current tasks.
 * It parses the user input.
 */
public class ListCommand extends Command{

    /**
     * Constructs a new ListCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the List command, printing the list of current tasks.
     *
     * @param tasks   The task list which contains the list of tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     */
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        Ui.showLine();
        tasks.printTasks();
        Ui.showLine();
    }
    @Override
    public boolean isExit() {
        return false;
    }

}

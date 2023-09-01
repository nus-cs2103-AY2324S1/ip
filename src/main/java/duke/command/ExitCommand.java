package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * The ExitCommand class represents a command to exit the application.
 * It parses the user input.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Exit command, adding a new deadline task to the task list.
     *
     * @param tasks   The task list from which the tasks will be read from.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws DukeException If there is an error writing to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showLine();
        try{
            storage.writeTasksToFile(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Error in writing taskList to file!");
        }
    }
    @Override
    public boolean isExit() {
        return true;
    }
}

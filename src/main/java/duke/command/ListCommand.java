package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command that prints the tasks in a list.
 */
public class ListCommand implements Command {

    /**
     * Prints the task in a list.
     *
     * @param tasks The task list containing the tasks.
     * @param ui    The user interface used to print the list.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws DukeException {
        String msg = ui.getLastMsg();
        String[] parts = msg.toLowerCase().split("\\s+");
        if (parts.length >= 2) { // If the date is provided
            ui.respond(tasks.getbyDate(parts[1]));
        } else {
            ui.respond(tasks.toString());
        }
        return false;
    }
}

package duke.command;
import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a command to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by printing out all the tasks.
     *
     * @param tasks   The list of tasks to be printed.
     * @param ui      The user interface handler.
     * @param storage The storage handler.
     * @throws DukeException If there's an issue executing the list command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            int tem_order = i + 1;
            System.out.println(tem_order + "." + tasks.getTask(i));
        }
    }

    /**
     * Indicates whether the command should exit the application.
     *
     * @return false, as 'list' command doesn't terminate the application.
     */
    @Override
    public boolean isExit() {

        return false;
    }
}

package jo.command;

import java.time.LocalDate;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `CheckCommand` class represents a command for checking tasks with a specified deadline date in the `Jo` application.
 */
public class CheckCommand extends Command {

    private LocalDate deadline;

    /**
     * Constructs a `CheckCommand` object with the specified deadline date to be checked.
     *
     * @param deadline The deadline date for which tasks will be checked.
     */
    public CheckCommand(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the command, searching for tasks with the specified deadline and displaying the result.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for loading and saving tasks to a file.
     * @throws JoException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.searchResult(this.deadline, tasks.search(this.deadline));
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since checking tasks by deadline does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

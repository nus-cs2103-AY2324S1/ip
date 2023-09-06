package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * Class for few types of command that
 * can be executed in the application.
 */
public class Command {
    boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command with the provided task list, user interface, marked status and
     * whether it is loading from memory
     *
     * @param tasks  The task list.
     * @param ui The user interface.
     * @param marked  task marked or unmarked
     * @param load whether the task is being loaded from memory
     */
    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
    }

    /**
     * indicate to Duke class whether it is time to end the program
     *
     * @return boolean of whether to end the program
     */
    public boolean isExit() {
        return this.isExit;
    }
}

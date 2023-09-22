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
    private boolean hasExit;

    public Command() {
        this.hasExit = false;
    }

    /**
     * Executes the command with the provided task list, user interface, marked status and
     * whether it is loading from memory
     *
     * @param tasks  The task list.
     * @param ui     The user interface.
     * @param status task marked or unmarked and whether the task is being loaded from memory
     */
    public String execute(TaskList<Task> tasks, Ui ui, boolean... status) throws DukeException {
        return "";
    }


    /**
     * indicate to duke.Husky class whether it is time to end the program
     *
     * @return boolean of whether to end the program
     */
    public boolean hasExit() {
        return this.hasExit;
    }

    public void setHasExit(boolean bool) {
        this.hasExit = bool;
    }
}

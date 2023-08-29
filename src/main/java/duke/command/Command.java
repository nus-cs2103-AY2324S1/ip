package duke.command;
//public enum duke.command.Command {
//    BYE,
//    LIST,
//    MARK,
//    UNMARK,
//    TODO,
//    DEADLINE,
//    EVENT,
//    DELETE,
//}

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes the command. This method is abstract and must be implemented by
     * subclasses. By default, it throws a DukeException.
     *
     * @param taskList list of tasks
     * @param ui       user interface
     * @param storage  storage
     * @throws DukeException if there is an error executing the command
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
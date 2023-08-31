package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

/**
 * Represents a command to be executed on the task list.
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

}

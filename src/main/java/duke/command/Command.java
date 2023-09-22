package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;


/**
 * Represents a command to be executed on the task list.
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui) throws DukeException;
}

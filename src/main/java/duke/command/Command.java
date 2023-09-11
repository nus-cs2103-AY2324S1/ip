package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks) throws DukeException;
}

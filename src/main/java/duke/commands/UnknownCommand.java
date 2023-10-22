package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents Unknown command to be executed
 */
public class UnknownCommand extends Command {
    public UnknownCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String execute() throws DukeException {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }
}

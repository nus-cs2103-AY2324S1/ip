package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to find a Task.
 */
public class FindCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "find";
    private final String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    @Override
    protected void validate(String arguments) throws DukeException {

        // Validate Inherited Rules
        super.validate(arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
      
        // Execute default statements
        Command.super.execute(taskList, ui, storage);

        ui.sendMessage(taskList.getTasksMatchingQuery(this.argument));
    }
}

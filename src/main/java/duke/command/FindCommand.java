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
        super.validate(arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(argument)) {
                output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        if (output.length() == 0) {
            ui.sendMessage("No Items in List");
        } else {
            ui.sendMessage("Here are the matching tasks in your list:\n" + output);
        }
    }
}

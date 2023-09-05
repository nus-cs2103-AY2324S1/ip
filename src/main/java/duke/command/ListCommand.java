package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to list all tasks within the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the given ListCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to be printed.
     * @param ui The UI to print the output onto.
     * @param storage The storage to save and update tasks (if needed).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.print((i + 1) + "." + tasks.getTaskString(i + 1));
        }
    }

    /**
     * Gets the command type for the ListCommand.
     *
     * @return List.
     */
    @Override
    public String getCommandType() {
        return "List";
    }
}

package commands;

import data.TaskList;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

/**
 * The ListCommand class.
 * Handles the display of the entire list of tasks
 * stored in {@link TaskList}.
 */
public class ListCommand extends Command {

    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage, UiCli uiCli) {
        if (tasks.isEmpty()) {
            return new UiMessage(new String[] {
                "Nothing stored."
            });
        }

        String[] formatTasks = new String[tasks.getSize() + 1];
        formatTasks[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            formatTasks[i + 1] = String.format(
                "%d.%s",
                i + 1, tasks.getTask(i).toString()
            );
        }
        return new UiMessage(formatTasks);
    }
}

package commands;

import constants.Message;
import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Deletes a task from the address book.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": list the tasks in the task list.\n"
            + "Example: " + COMMAND_WORD;

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String list = tasks.listTasks();
            ui.printMessage(Message.LIST_TASKS, list);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}


package sam.commands;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

/**
 * List all tasks from the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": find similar tasks in the task list.\n"
            + "Example: " + COMMAND_WORD + " book";
    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList list = tasks.findTasks(search);
            ui.printMessage(Message.SEARCH_TASK_SUCCESS, list.listTasks());
            return new CommandResult(Message.SEARCH_TASK_SUCCESS, list.listTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return new IncorrectCommand( e.getMessage()).execute(tasks, ui, storage);
        }
    }
}


package sam.commands;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.tasks.Task;

import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private Integer index;

    public DeleteTaskCommand(Integer index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(index);
            ui.printMessage(Message.DELETE_TASK, "\t" + removedTask, tasks.getTaskCountSummary());
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.DELETE_TASK, "\t" + removedTask, tasks.getTaskCountSummary());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return new IncorrectCommand(e.getMessage()).execute(tasks, ui, storage);
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
            return new IncorrectCommand(Message.FAILED_TO_SAVE + e.getMessage()).execute(tasks, ui, storage);
        }
    }
}

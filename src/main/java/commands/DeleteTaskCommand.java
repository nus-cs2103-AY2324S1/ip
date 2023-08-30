package commands;

import constants.Message;
import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Task;

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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(index);
            ui.printMessage(Message.DELETE_TASK, "\t" + removedTask, tasks.taskCountSummary());
            storage.saveTasksToFile(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
        }
    }
}

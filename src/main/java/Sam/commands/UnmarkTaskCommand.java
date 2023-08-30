package sam.commands;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

import java.io.IOException;

/**
 * Mark a task as not done from the task list.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": mark the tasks identified by the index number as incomplete.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + "1";
    private Integer index;

    public UnmarkTaskCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTaskAsNotDone(index);
            ui.printMessage(Message.UNMARK_TASK, "\t" + tasks.getTask(index));
            storage.saveTasksToFile(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
        }
    }
}


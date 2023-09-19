package sam.commands;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

import java.io.IOException;

/**
 * Mark a task as done from the task list.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": mark the tasks identified by the index number as completed.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + "1";
    private Integer index;

    public MarkTaskCommand(Integer index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTaskAsDone(index);
            ui.printMessage(Message.MARK_TASK, "\t" + tasks.getTask(index));
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.MARK_TASK, "\t" + tasks.getTask(index));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return new IncorrectCommand(e.getMessage()).execute(tasks, ui, storage);
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
            return new IncorrectCommand(Message.FAILED_TO_SAVE
                    + e.getMessage()).execute(tasks, ui, storage);
        }
    }
}

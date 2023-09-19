package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;

import java.io.IOException;

/**
 * Mark a task as done from the task list.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": tag the tasks identified by the index number with tags.\n"
            + "Parameters: INDEX #DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " 1 #fun";
    private Integer index;
    private String description;

    public AddTagCommand(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(index).addTag(description);
            ui.printMessage(Message.TAG_TASK, "\t" + tasks.getTask(index));
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.TAG_TASK, "\t" + tasks.getTask(index));
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
            return new IncorrectCommand(Message.FAILED_TO_SAVE
                    + e.getMessage()).execute(tasks, ui, storage);
        }
    }
}

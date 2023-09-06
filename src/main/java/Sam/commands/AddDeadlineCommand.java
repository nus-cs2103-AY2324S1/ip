package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.tasks.Deadline;
import sam.tasks.Task;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Adds a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the task list. \n"
            + "Parameters: DESCRIPTION /by DATETIME \n"
            + "Example: " + COMMAND_WORD
            + " return homework /by 2023-11-15 0800 ";
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(this.description, this.by);
            tasks.addTask(deadline);
            ui.printMessage(Message.ADD_TASKS, "\t" + deadline, tasks.getTaskCountSummary());
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.ADD_TASKS, "\t" + deadline, tasks.getTaskCountSummary());
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
            return new IncorrectCommand(Message.FAILED_TO_SAVE + e.getMessage()).execute(tasks, ui, storage);
        }
    }

}


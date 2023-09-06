package sam.commands;

import sam.constants.Message;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.tasks.Task;
import sam.tasks.Event;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list. \n"
            + "Parameters: DESCRIPTION /from DATETIME /to DATETIME \n"
            + "Example: " + COMMAND_WORD
            + " go to project meeting /from 2019-10-15 1800 /to 2020-12-2 2000";

    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task event = new Event(this.description, this.from, this.to);
            tasks.addTask(event);
            ui.printMessage(Message.ADD_TASKS, "\t" + event, tasks.getTaskCountSummary());
            storage.saveTasksToFile(tasks);
            return new CommandResult(Message.ADD_TASKS, "\t" + event, tasks.getTaskCountSummary());
        } catch (IOException e) {
            return new IncorrectCommand(Message.FAILED_TO_SAVE + e.getMessage()).execute(tasks, ui, storage);
        }
    }

}

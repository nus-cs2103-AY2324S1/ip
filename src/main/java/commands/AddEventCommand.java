package commands;

import constants.Message;
import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Task;
import tasks.Event;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Adds a person to the address book.
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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task event = new Event(this.description, this.from, this.to);
            tasks.addTask(event);
            ui.printMessage(Message.ADD_TASKS, "\t" + event, tasks.taskCountSummary());
            storage.saveTasksToFile(tasks);
        } catch (IOException e) {
            ui.showError(Message.FAILED_TO_SAVE + e.getMessage());
        }
    }

}

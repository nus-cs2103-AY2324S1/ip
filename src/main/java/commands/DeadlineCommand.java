package commands;

import java.time.LocalDateTime;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Deadline;
import data.tasks.Task;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

/**
 * The DeadlineCommand class.
 * Handles creating a new {@link Deadline}.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    /**
     * The constructor method of the DeadlineCommand class.
     * Takes in the description and date for a {@link Deadline}.
     * 
     * @param description The description of the deadline.
     * @param deadline The date of the deadline.
     */
    public DeadlineCommand(String description,
                           LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public UiMessage execute(TaskList tasks,
            Storage storage,
            UiCli uiCli) throws DukeException {
        Task dl = new Deadline(description, deadline);
        tasks.add(dl);
        storage.update(tasks);
        return new UiMessage(new String[] {
            "Okie! I've added a new DEADLINE:",
            "  " + dl.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}

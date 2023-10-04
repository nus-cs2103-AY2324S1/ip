package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * The AddEventCommand is the command for adding an Event into the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * The constructor for an AddEventCommand.
     *
     * @param description The description of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the AddEventCommand.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException On input or file error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, start, end);
        tasks.add(event);
        ui.printAddSuccessMessage(event, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Checks whether the command is an Exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

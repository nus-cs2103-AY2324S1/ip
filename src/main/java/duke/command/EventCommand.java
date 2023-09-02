package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a event task to Duke's task list.
 */
public class EventCommand extends Command {

    private String description;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an EventCommand with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event task.
     * @param to          The end date of the event task.
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showEventMessage(taskList.addEvent(this.description, this.from, this.to), taskList.getSize());
    }
}
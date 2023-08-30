package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;
import task.Event;

import java.time.LocalDate;

/**
 * Represents an event command where when executed, adds a task with a start and end to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an EventCommand using the description, start date and end date.
     *
     * @param description The description to the task.
     * @param start The start date.
     * @param end The end date.
     */
    public EventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.addTask(new Event(description, start, end)));
        diskManager.saveToDisk(taskManager);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof EventCommand) {
            EventCommand temp = (EventCommand) other;
            return temp.description.equals(this.description)
                    && temp.start.equals(this.start)
                    && temp.end.equals(this.end);
        }
        return false;
    }
}

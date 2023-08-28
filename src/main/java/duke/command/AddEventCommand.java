package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    public AddEventCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (description.trim().isEmpty()) {
                throw new EmptyDescriptionException("event");
            }

            Event newEvent = new Event(description, dateTime);
            taskList.add(newEvent);
            ui.showAdd(newEvent, taskList.getLength());

        } catch (EmptyDescriptionException e) {
            ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            ui.showInvalidDateTimeFormat();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

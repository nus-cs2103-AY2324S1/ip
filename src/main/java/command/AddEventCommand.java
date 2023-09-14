package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Event;

import java.util.Date;

public class AddEventCommand extends Command {
    private String description;
    private String eventAt;
    private Date eventDate;

    public AddEventCommand(String description, String deadlineBy) {
        this.description = description;
        this.eventAt = deadlineBy;
    }

    public AddEventCommand(String description, Date deadlineDate) {
        this.description = description;
        this.eventDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Event event = validateEvent();
        tasks.add(event);
        ui.printAddSuccessMessage(event, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    Event validateEvent() {
        Event event;
        if (eventDate == null) {
            event = new Event(description, eventAt);
        } else {
            event = new Event(description, eventDate);
        }
        return event;
    }
}

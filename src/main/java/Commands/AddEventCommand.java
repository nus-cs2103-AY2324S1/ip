package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.Event;
public class AddEventCommand implements Command {
    private Event event;

    public AddEventCommand(String eventName, String eventStart, String eventEnd) {
        this.event = new Event(eventName, false, eventStart, eventEnd);
    }

    public Event getEvent() {
        return this.event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.event);
        ui.printTaskAddedMessage(this.event, tasks);
    }
}

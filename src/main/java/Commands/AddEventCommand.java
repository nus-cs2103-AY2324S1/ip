package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.Event;
public class AddEventCommand implements Command {
    private String eventName;
    private String eventStart;
    private String eventEnd;

    public AddEventCommand(String eventName, String eventStart, String eventEnd) {
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task event = new Event(eventName, false, eventStart, eventEnd);
        tasks.addTask(event);
        ui.printTaskAddedMessage(event, tasks);

    }
}

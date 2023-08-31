import java.io.IOException;
import java.util.Date;

public class AddEventCommand extends Command {
    private final String description;
    private final Date from;
    private final Date to;

    public AddEventCommand(String description, Date from, Date to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Event newEvent = new Event(description, from, to);
        taskList.addTask(newEvent);
        Storage.save(newEvent);
        ui.showTaskAdded(newEvent, taskList.countTasks());
    }
}

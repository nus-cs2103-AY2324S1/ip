import java.time.LocalDate;
import java.util.List;

/**
 * Command to list all events that occur on date
 */
public class ListByDateCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a ListByDateCommand object
     * @param date Date to query for
     */
    public ListByDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasksOnDate = taskList.getTaskOnDate(date);
        ui.printToScreen("Here are the tasks occurring on " + date + ":");
        for (Task t : tasksOnDate) {
            ui.printToScreen(t.toString());
        }
    }
}

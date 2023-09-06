package atlas.commands;

import java.time.LocalDate;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;

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
        int idx = 0;
        for (Task t : tasksOnDate) {
            ui.printToScreen(String.format("%d. %s", ++idx, t.toString()));
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> tasksOnDate = taskList.getTaskOnDate(date);
        StringBuilder output = new StringBuilder("Here are the tasks occurring on " + date + ":\n");
        int idx = 0;
        for (Task t : tasksOnDate) {
            output.append(String.format("%d. %s\n", ++idx, t.toString()));
        }
        return output.toString();
    }
}

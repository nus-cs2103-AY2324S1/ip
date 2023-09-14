package atlas.commands;

import java.time.LocalDate;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;

/**
 * Command to list all events that occur on date
 */
public class ListByDateCommand extends MultiTaskCommand {
    private final LocalDate date;

    /**
     * Constructs a ListByDateCommand object
     * @param date Date to query for
     */
    public ListByDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> tasksOnDate = taskList.getTaskOnDate(date);
        final String outputHeaderMessage = "Here are the tasks occurring on " + date + ":";
        return generateListOutput(tasksOnDate, outputHeaderMessage);
    }
}

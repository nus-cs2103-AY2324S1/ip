package atlas.commands;

import java.time.LocalDate;
import java.util.List;

import atlas.components.Parser;
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
        assert taskList != null;
        List<Task> tasksOnDate = taskList.getTaskOnDate(date);
        final String outputHeaderMessage = "I've held up the heavens for so long that"
                + " time became a meaningless construct, mortal. But if you insist, "
                + "these are the labours occurring on " + date.format(Parser.DATE_FORMATTER) + ":";
        return generateListOutput(tasksOnDate, outputHeaderMessage);
    }
}

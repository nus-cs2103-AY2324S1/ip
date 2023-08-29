package commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import errors.DotException;
import tasks.TaskList;
import ui.Ui;

/**
 * Command to list out all tasks happening on a given date.
 */
public class WhatsgoingonCommand extends Command {

    private final LocalDateTime parsedLocalDateTime;
    private final TaskList dotTaskList;

    /**
     * Constructor for WhatsgoingonCommand.
     *
     * @param parsedLocalDateTime This is the LocalDateTime object representing queried Date.
     * @param dotTaskList         This is the TaskList which encapsulates the Task and operations.
     */
    public WhatsgoingonCommand(LocalDateTime parsedLocalDateTime,
                               TaskList dotTaskList) {
        this.parsedLocalDateTime = parsedLocalDateTime;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Queries and displays all tasks falling on parsedLocalDateTime.
     *
     * @throws DotException On detected error
     */
    public void execute() throws DotException {
        ArrayList<String> queriedTasks = dotTaskList.getDisplayForTasksFallingOnDate(parsedLocalDateTime);
        Ui.displayArrayList(queriedTasks);
    }
}

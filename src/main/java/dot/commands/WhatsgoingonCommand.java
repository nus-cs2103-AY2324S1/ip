package commands;

import errors.DotException;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WhatsgoingonCommand extends Command {

    private final LocalDateTime parsedLocalDateTime;
    private final TaskList dotTaskList;
    public WhatsgoingonCommand(LocalDateTime parsedLocalDateTime,
                               TaskList dotTaskList) {
        this.parsedLocalDateTime = parsedLocalDateTime;
        this.dotTaskList = dotTaskList;
    }
    public void execute() throws DotException {
        ArrayList<String> queriedTasks = dotTaskList.getDisplayForTasksFallingOnDate(parsedLocalDateTime);
        Ui.displayArrayList(queriedTasks);
    }
}

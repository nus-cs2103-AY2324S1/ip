package commands;

import errors.DotException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.io.File;

public class EventCommand extends Command {
    private final String description;
    private final String start;
    private final String end;
    private final TaskList dotTaskList;
    private final String dataFilePathname;

    public EventCommand(String description, String start, String end,
                        TaskList dotTaskList, String dataFilePathname) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.dotTaskList = dotTaskList;
        this.dataFilePathname = dataFilePathname;
    }

    @Override
    public void execute() throws DotException {
        Task newEventTask = new Event(this.description, this.start, this.end);
        dotTaskList.addTask(newEventTask);
        dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
    };
}

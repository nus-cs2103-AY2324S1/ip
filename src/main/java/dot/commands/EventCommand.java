package dot.commands;

import dot.errors.DotException;
import dot.tasks.Event;
import dot.tasks.Task;
import dot.tasks.TaskList;

public class EventCommand extends Command {
    private final String description;
    private final String start;
    private final String end;
    private final TaskList dotTaskList;

    public EventCommand(String description, String start, String end,
                        TaskList dotTaskList) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        Task newEventTask = new Event(this.description, this.start, this.end);
        dotTaskList.addTask(newEventTask);
        dotTaskList.saveTaskListToStorage();
    };
}

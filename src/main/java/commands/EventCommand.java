package commands;

import errors.DotException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

/**
 * Command to create an event.
 */
public class EventCommand extends Command {
    private final String description;
    private final String start;
    private final String end;
    private final TaskList dotTaskList;

    /**
     * Constructor for EventCommand.
     *
     * @param description This is the description of the event.
     * @param start       This is the description of datetime of the start of the event.
     * @param end         This is the description of datetime of the end of the event.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public EventCommand(String description, String start, String end,
                        TaskList dotTaskList) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Creates and inserts the Event into dotTaskList.
     *
     * @throws DotException On detected error.
     */
    @Override
    public void execute() throws DotException {
        Task newEventTask = new Event(this.description, this.start, this.end);
        dotTaskList.addTask(newEventTask);
        dotTaskList.saveTaskListToStorage();
    }

}

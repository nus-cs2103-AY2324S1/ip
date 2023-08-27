package commands;

import exceptions.ShibaException;
import tasks.EventTask;
import tasks.TaskList;

public class EventCommand extends ShibaCommand {
    private final String fullCmd;

    /**
     * Constructor for EventCommand, which adds a task of type event
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public EventCommand(TaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        EventTask event = EventTask.fromCmd(fullCmd);
        addTask(event);
    }
}

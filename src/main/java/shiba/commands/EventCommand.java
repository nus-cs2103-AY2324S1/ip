package shiba.commands;

import shiba.exceptions.ShibaException;
import shiba.tasks.EventTask;
import shiba.tasks.PersistentTaskList;

/**
 * Represents a command to add an event task
 */
public class EventCommand extends ShibaCommand {
    private final String fullCmd;

    /**
     * Constructor for EventCommand, which adds a task of type event
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public EventCommand(PersistentTaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        EventTask event = EventTask.fromCmd(fullCmd);
        addTask(event);
    }
}

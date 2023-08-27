package commands;

import exceptions.ShibaException;
import tasks.DeadlineTask;
import tasks.TaskList;

public class DeadlineCommand extends ShibaCommand {
    private final String fullCmd;

    /**
     * Constructor for DeadlineCommand, which adds a task of type deadline
     *
     * @param tasks Current state of task list
     * @param cmd  Full command string
     */
    public DeadlineCommand(TaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        DeadlineTask deadline = DeadlineTask.fromCmd(fullCmd);
        addTask(deadline);
    }
}

package shiba.commands;

import shiba.exceptions.ShibaException;
import shiba.tasks.TaskList;
import shiba.tasks.TodoTask;

public class TodoCommand extends ShibaCommand {
    private final String fullCmd;

    /**
     * Constructor for TodoCommand, which adds a task of type todo
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public TodoCommand(TaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        TodoTask todo = TodoTask.fromCmd(fullCmd);
        addTask(todo);
    }
}

package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Unmark command.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Instantiates a new Unmark command.
     *
     * @param index    The index of the task.
     * @param taskList The task list.
     */
    public UnmarkCommand(int index, TaskList taskList) {
        super(taskList);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = super.getTaskList().unmarkTask(index);
        return "Nice! I've marked this task as done:\n " + task;
    }
}

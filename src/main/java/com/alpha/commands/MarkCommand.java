package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Instantiates a new Mark command.
     *
     * @param index    The index of the task.
     * @param taskList The task list.
     */
    public MarkCommand(int index, TaskList taskList) {
        super(taskList);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = super.getTaskList().markTask(index);
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}

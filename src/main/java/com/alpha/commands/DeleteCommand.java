package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Instantiates a new Delete command.
     *
     * @param index    The index of the task.
     * @param taskList The task list.
     */
    public DeleteCommand(int index, TaskList taskList) {
        super(taskList);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = super.getTaskList().deleteTask(index);
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + super.getTaskListSize() + " tasks in the list.\n";
    }
}

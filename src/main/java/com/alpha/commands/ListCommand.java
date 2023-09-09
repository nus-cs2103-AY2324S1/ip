package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type List command.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new List command.
     *
     * @param taskList The task list.
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : getTaskList().getTasks()) {
            String row = count++ + "." + task.toString() + "\n";
            sb.append(row);
        }
        return sb.toString();
    }
}

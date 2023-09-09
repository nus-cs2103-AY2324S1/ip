package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.tasks.ToDo;

/**
 * The type To do command.
 */
public class ToDoCommand extends Command {

    private final Task task;

    /**
     * Instantiates a new To do command.
     *
     * @param name    The name of the task.
     * @param taskList The task list.
     */
    public ToDoCommand(String name, TaskList taskList) {
        super(taskList);
        task = new ToDo(name);
    }

    @Override
    public String execute() {
        getTaskList().addTask(task);
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + super.getTaskListSize() + " tasks in the list.\n";
    }
}

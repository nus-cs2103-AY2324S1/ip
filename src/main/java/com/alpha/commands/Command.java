package com.alpha.commands;

import com.alpha.tasks.TaskList;

/**
 * The type Command.
 */
public abstract class Command {

    private final TaskList taskList;

    /**
     * Instantiates a new Command.
     *
     * @param taskList The task list.
     */
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets task list size.
     *
     * @return The size of the task list.
     */
    public int getTaskListSize() {
        return taskList.getSize();
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Execute the command.
     *
     * @return the string
     */
    public abstract String execute();
}

package com.ducky.command;

import com.ducky.logic.Storage;
import com.ducky.logic.TaskList;

/**
 * Represents a command that lists all tasks in Ducky's task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command that lists all tasks in Ducky's task list.
     */
    public ListCommand() {}

    /**
     * Returns each task on each line as their printable form as a string.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String containing all existing tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getPrintableList();
    }
}

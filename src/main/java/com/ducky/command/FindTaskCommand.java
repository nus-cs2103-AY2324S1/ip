package com.ducky.command;

import com.ducky.logic.Storage;
import com.ducky.logic.TaskList;

/**
 * Represents a command that finds tasks containing a given query.
 */
public class FindTaskCommand extends Command {

    private final String queryString;

    /**
     * Constructs a command to find tasks containing the specified query.
     * @param queryString Query to find tasks in task list.
     */
    public FindTaskCommand(String queryString) {
        this.queryString = queryString;
    }

    /**
     * Returns query results with each task on each line as their printable form as a string.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String containing all tasks in query result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getTaskQueryResult(this.queryString);
    }
}

package com.alpha.commands;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Find command.
 */
public class FindCommand extends Command {

    private final String searchString;

    /**
     * Instantiates a new Find command.
     *
     * @param searchString The search string.
     * @param taskList     The task list.
     */
    public FindCommand(String searchString, TaskList taskList) {
        super(taskList);
        this.searchString = searchString;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : getTaskList().getTasks()) {
            if (task.getName().contains(searchString)) {
                String row = count++ + "." + task + "\n";
                sb.append(row);
            }
        }
        return sb.toString();
    }


}

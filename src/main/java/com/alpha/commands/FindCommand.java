package com.alpha.commands;

import java.util.ArrayList;
import java.util.List;

import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type Find command.
 */
public class FindCommand extends Command {
    /**
     * Instantiates a new Find command.
     *
     * @param args The arguments of the Command.
     */
    public FindCommand(String args) {
        super(args);
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> result = new ArrayList<>();
        String searchString = Parser.getFindSearchString(getArgs());
        for (Task task : taskList.getTasks()) {
            if (task.getName().contains(searchString)) {
                result.add(task);
            }
        }
        return ui.displayTasks(result);
    }
}

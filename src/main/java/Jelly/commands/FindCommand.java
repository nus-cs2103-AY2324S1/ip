package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;
import Jelly.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The specified keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.find(keyword);
        ui.printList(matchingTasks);
    }
}

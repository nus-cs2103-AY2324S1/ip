package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.ToDo;

import java.io.IOException;

public class FindCommand extends Command {

    private String searchKeywords;

    public FindCommand(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Executing Find Command");
        TaskList searchResults = taskList.findTasks(searchKeywords);
        ui.showTaskList(searchResults);
    }

}

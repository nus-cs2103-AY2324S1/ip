package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        TaskList results = new TaskList();
        for (Task task : taskList.getToDos()) {
            if (task.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }
        ui.showSearchResults(results);
    }
}

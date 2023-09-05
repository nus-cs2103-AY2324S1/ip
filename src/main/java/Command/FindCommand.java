package Command;

import Main.Storage;
import Main.UI;
import Task.Task;
import Task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String fullCommand) {
        this.keyword = fullCommand.substring(5);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        TaskList findList = new TaskList();

        for (Task t : taskList.getTaskArrayList()) {
            if(t.getDescription().contains(this.keyword)) {
                findList.addTask(t);
            }
        }

        ui.list(findList.getTaskArrayList(), true);
    }
}

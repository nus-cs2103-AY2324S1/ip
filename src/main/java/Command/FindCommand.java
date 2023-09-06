package command;

import main.Storage;
import main.UI;
import task.Task;
import task.TaskList;

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

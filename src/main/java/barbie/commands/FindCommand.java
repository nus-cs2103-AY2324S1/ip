package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        return Ui.findTasks(taskList, this.keyword);
    }
}

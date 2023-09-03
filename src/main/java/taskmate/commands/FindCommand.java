package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    String query;

    public FindCommand(String query) {
        this.query = query.toLowerCase();
        this.commandType = "Find";
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks.getAllTasks()) {
            boolean isMatchingTask = t.toString().toLowerCase().contains(this.query);
            if (isMatchingTask) {
                matchingTasks.add(t);
            }
        }
        ui.printMatchingTasks(matchingTasks);
    }
}

package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String detail) {
        super(detail);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        ArrayList<Task> contain = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(this.detail)) {
                contain.add(tasks.get(i));
            }
        }
        boolean isGreaterThan1 = contain.size() > 1;
        String result = "Here " + (isGreaterThan1 ? "are" : "is") + " are the matching " +
                (isGreaterThan1 ? "tasks" : "task") + " in your list:\n";
        for (int i = 0; i < contain.size(); i++) {
            result += (i + 1) + ". " + contain.get(i) + "\n";
        }
        result += Ui.showLine();
        result += "\n";
        return result;
    }
}

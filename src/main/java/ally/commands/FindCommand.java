package ally.commands;

import java.util.ArrayList;

import ally.Storage;
import ally.Ui;
import ally.tasks.AllyList;
import ally.tasks.Task;



/**
 * FindCommand inherits from Commands.
 */
public class FindCommand extends Commands {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void run(AllyList allyList, Ui ui, Storage storage) {
        ArrayList<Task> matchTasks = new ArrayList<>(100);
        for (int i = 0, len = allyList.getSize(); i < len; i++) {
            if (allyList.getTask(i).getDescription().contains(this.description)) {
                matchTasks.add(allyList.getTask(i));
            }
        }
        ui.showMatchingTask(matchTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

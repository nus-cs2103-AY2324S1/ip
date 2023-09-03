package Ally.Commands;

import Ally.Storage;
import Ally.Tasks.AllyList;
import Ally.Tasks.Task;
import Ally.Ui;

import java.util.ArrayList;

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

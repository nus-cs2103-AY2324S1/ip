package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private final int INDEX;
    public MarkCommand(int index) {
        INDEX = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert INDEX > 0 : "Index should be positive.";
        if (INDEX <= 0 || INDEX > taskList.size()) {
            return ("Invalid input");
        }
        if (taskList.get(INDEX - 1).getTaskStatus().equals("X")) {
            return ("Uh, it appears that you've finished this task o.o");
        } else {
            taskList.get(INDEX - 1).markAsDone();
            storage.saveAndExit(taskList);
            return ("Good job! I've marked this task as done :)");
        }
    }
}

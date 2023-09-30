package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int INDEX;

    public UnmarkCommand(int index) {
        INDEX = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert INDEX > 0 : "Index should be positive.";
        if (INDEX <= 0 || INDEX > taskList.size()) {
            return ("Invalid input");
        }
        if (taskList.get(INDEX - 1).getTaskStatus().equals(" ")) {
            return ("Yo,you can't unmark something you haven't done yet o.o");
        } else {
            taskList.get(INDEX - 1).markAsUndone();
            storage.saveAndExit(taskList);
            return ("Bad job! I've marked this task as not done :(");
        }
    }
}

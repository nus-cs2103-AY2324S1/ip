package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > 100) {
            return ("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            if (taskList.get(index - 1).getTaskStatus() == " ") {
                return ("Yo,you can't unmark something you haven't done yet o.o");
            } else {
                taskList.get(index - 1).markAsUndone();
                return ("Bad job! I've marked this task as not done :(");
            }
        } else {
            return ("Invalid input");
        }
    }

}

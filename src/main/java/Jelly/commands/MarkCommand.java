package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;

public class MarkCommand extends Command {

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > 100) {
            return ("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            if (taskList.get(index - 1).getTaskStatus() == "X") {
                return ("Uh, it appears that you've finished this task o.o");
            } else {
                taskList.get(index - 1).markAsDone();
                return ("Good job! I've marked this task as done :)");
            }
        } else {
            return ("Invalid input");
        }
    }
}

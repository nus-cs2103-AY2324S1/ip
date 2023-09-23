package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

public class MarkCommand extends Command {

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert index > 0 : "Index should be positive.";
        if (index <= 0 || index > 100) {
            return ("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            if (taskList.get(index - 1).getTaskStatus() == "X") {
                return ("Uh, it appears that you've finished this task o.o");
            } else {
                taskList.get(index - 1).markAsDone();
                storage.saveAndExit(taskList);
                return ("Good job! I've marked this task as done :)");
            }
        } else {
            return ("Invalid input");
        }
    }
}

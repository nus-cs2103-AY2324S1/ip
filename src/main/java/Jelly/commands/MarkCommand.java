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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > 100) {
            System.out.println("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            if (taskList.get(index - 1).getTaskStatus() == "X") {
                System.out.println("Uh, it appears that you've finished this task o.o");
            } else {
                taskList.get(index - 1).markAsDone();
                System.out.println("Good job! I've marked this task as done :)");
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}

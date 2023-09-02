package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) {
        this.printCommand(taskList);
    }

    @Override
    public void printCommand(TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}

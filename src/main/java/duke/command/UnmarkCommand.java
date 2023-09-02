package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.getTask(this.index - 1).markAsUndone();
        this.printCommand(taskList);
        storage.writeData(taskList.convertToFileContent());
    }

    @Override
    public void printCommand(TaskList taskList) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + taskList.getTask(this.index - 1).printTask());
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.getTask(this.index - 1).markAsDone();
        this.printCommand(taskList);
        storage.writeData(taskList.convertToFileContent());
    }

    @Override
    public void printCommand(TaskList taskList) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + taskList.getTask(this.index - 1).printTask());
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
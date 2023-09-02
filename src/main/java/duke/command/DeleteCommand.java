package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        this.printCommand(taskList);
        taskList.removeTask(this.index - 1);
        storage.writeData(taskList.convertToFileContent());
    }

    @Override
    public void printCommand(TaskList taskList) {
        Task temp = taskList.getTask(this.index - 1);
        System.out.println("JonBird:\n\tNoted. I've removed this task:");
        System.out.println("\t\t" + temp.printTask());
        System.out.println("\tNow you have " + (taskList.size() - 1) + " tasks in the list.");
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}

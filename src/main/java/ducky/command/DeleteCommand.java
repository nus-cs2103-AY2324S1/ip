package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;
import ducky.task.Task;

public class DeleteCommand extends Command {

    private final int inputIndex;

    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException {
        int deleteIndex = this.inputIndex - 1;
        Task deletedTask = taskList.deleteTask(deleteIndex);
        storage.save(taskList);
        ui.showMessagePerLine(
                "Okay! I've removed this task:",
                deletedTask.toString(),
                taskList.getListLengthStatus()
        );
    }
}

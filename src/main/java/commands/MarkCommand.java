package commands;

import exception.URChatBotException;
import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

public class MarkCommand extends Command{
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        super("Mark");
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        if (tasks.getSize() < 1 || tasks.getSize() <= taskNumber) {
            throw new URChatBotException("OOPS!!! No task to mark!");
        }
        Task markedTask = tasks.getTasks().get(taskNumber);
        markedTask.markAsDone();
        Storage.save(tasks);
        String taskName = markedTask.toString();
        ui.showMarkMessage(taskName);
    }
}

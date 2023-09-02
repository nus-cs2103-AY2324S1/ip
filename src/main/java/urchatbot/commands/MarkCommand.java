package urchatbot.commands;

import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

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

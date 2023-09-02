package urchatbot.commands;

import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

public class UnmarkCommand extends Command{
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super("Unmark");
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        if (tasks.getSize() < 1 || tasks.getSize() <= taskNumber) {
            throw new URChatBotException("OOPS!!! No task to mark!");
        }
        Task unmarkedTask = tasks.getTasks().get(taskNumber);
        unmarkedTask.markAsUnDone();
        Storage.save(tasks);
        String taskName = unmarkedTask.toString();
        ui.showUnmarkMessage(taskName);
    }
}

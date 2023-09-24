package urchatbot.commands;

import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

/**
 * Marks a task identified using it's displayed index as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs the MarkCommand class.
     *
     * @param taskNumber Task number to mark.
     */
    public MarkCommand(int taskNumber) {
        super("Mark");
        this.taskNumber = taskNumber;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        boolean isInvalidSize = tasks.getSize() < 1 || tasks.getSize() <= taskNumber;
        if (isInvalidSize) {
            throw new URChatBotException("OOPS!!! No task to mark!");
        }
        Task markedTask = tasks.getTasks().get(taskNumber);
        markedTask.markAsDone();
        Storage.save(tasks);
        String taskName = markedTask.toString();
        return ui.showMarkMessage(taskName);
    }
}

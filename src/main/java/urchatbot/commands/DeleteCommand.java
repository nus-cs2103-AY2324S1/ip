package urchatbot.commands;

import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * Deletes a task identified using it's displayed index from the tasklist.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    /**
     * Constructs the DeleteCommand class.
     *
     * @param taskNumber Task number to delete.
     */
    public DeleteCommand(int taskNumber) {
        super("Delete");
        this.taskNumber = taskNumber;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        if (tasks.getSize() < 1 || tasks.getSize() <= taskNumber) {
            throw new URChatBotException("OOPS!!! No task to delete!");
        }
        String deletedTask = tasks.getTasks().get(taskNumber).toString();
        tasks.deleteTask(taskNumber);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize == 0) {
            return ui.showDeleteMessage(deletedTask, taskSize);
        } else {
            return ui.showDeleteMessagePlural(deletedTask, taskSize);
        }

    }
}

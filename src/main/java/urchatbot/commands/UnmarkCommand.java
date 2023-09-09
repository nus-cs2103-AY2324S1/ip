package urchatbot.commands;

import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

/**
 * Marks a task identified using it's displayed index as undone.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs the UnmarkCommand class.
     *
     * @param taskNumber Task number to unmark.
     */
    public UnmarkCommand(int taskNumber) {
        super("Unmark");
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        if (tasks.getSize() < 1 || tasks.getSize() <= taskNumber) {
            throw new URChatBotException("OOPS!!! No task to mark!");
        }
        Task unmarkedTask = tasks.getTasks().get(taskNumber);
        unmarkedTask.markAsUnDone();
        Storage.save(tasks);
        String taskName = unmarkedTask.toString();
        return ui.showUnmarkMessage(taskName);
    }
}
